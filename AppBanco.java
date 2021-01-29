import java.util.ArrayList;
import java.util.Scanner;
import java.io.RandomAccessFile;
import java.util.Random;

public class AppBanco{
    
    public static ArrayList<Conta> listaConta = new ArrayList<>(); //arraylist onde será armazenado todos os objetos conta
    public static void main(String[] args) {
        //Extrair informações do arquivo de banco de dados
        String textoArquivo = ManipularArquivo.ler(); //ler o arquivo todo, o converte em uma única string e aloca em textoArquivo

        int qtdContas = encontrarQtdOcorrencias(textoArquivo,"/"); //encontrar o numero de contas baseado na quantidade de "/", ja que ao final de cada linha existe esse caracter
        
        String pessoa[] = new String[qtdContas]; //cada variavel pessoa armazenará uma linha do arquivo. Cada linha corresponde à informacoes de uma pessoa
        Conta[] conta = new Conta[qtdContas];  //criação dos objetos contas

        //inicializando cada posição do vetor conta como um objeto do tipo Conta
        for (int i=0; i<qtdContas; i++ )
            conta[i] = new Conta();

        //cada posição de pessoa[] gardará uma linha, ou seja, cada posição corresponde ás informações de uma pessoa
        for (int i=0; i<pessoa.length; i++ )
            pessoa[i] = textoArquivo.split("/")[i]; //quebrando o arquivo original em padrões, limitados pelo caracter "/". Cada linha finaliza no caracter "/"
        
        //O método setarDados da classe ManipularArquivo irá quebrar novamente a string pessoa no padrão do caracter ";", alocando cada informacao ao seu respectivo atributo da conta
        //SetarDados irá retornar uma conta ja preenchida com as informações de um cliente e através do método add, esse objeto conta será adicionado no ArrayList listaConta
        for (int i=0; i<pessoa.length; i++ )
            listaConta.add(ManipularArquivo.setarDados(conta[i],pessoa[i])); //

        String opcao;
        String lerCpf;
        Scanner input = new Scanner(System.in);

        System.out.println("===========================================================================BANCO NEXTOLL===========================================================================");
        while(true){
            int index = -1;
            System.out.print("\n|Tela de Login|\n\n1-Logar\t\t2-Criar conta\t\t3-Sair\n\nEscolha uma opcao: ");
            opcao = input.next();
            if (opcao.equals("3")){
                ManipularArquivo.salvar(listaConta);   //Ao finalizar o programa, as informações serão salvas!
                break;
            }else if (opcao.equals("1")){
                System.out.print("\nCpf: ");
                lerCpf = input.next();
                for (int i=0; i < listaConta.size(); i++){  //nesse laço, será procurado o cpf digitado, varrendo o arraylist comparando com os cpfs já cadastrados
                    if(listaConta.get(i).getCpf().equals(lerCpf)) 
                        index = i;                                      //se o cpf digitado existir, o indice de sua posição no arraylist será armazenado na variavel index pra ser usado mais pra frente
                }
                if (index == -1)   //se index for igual a -1, significa que o laço anterior não encontrou um cpf no banco de dados, pois ele é inicializado com o valor de -1 
                    System.out.println("\nCpf inválido!");
                else                                             //se o cpf foi encontrado, então o menu será aberto com a conta da posição de valor index.
                    abrirMenu(listaConta.get(index));
            }else if(opcao.equals("2"))
                abrirConta();
            else
                System.out.println("Opção inválida, tente novamente!");
        }
        input.close();
    }

    public static int encontrarQtdOcorrencias(String strFonte, String str){  //Encontrar o numero de vezes que a string str ocorre na string strFonte
        int pos = -1;
        int contador = 0;
        while (true) {
            pos = strFonte.indexOf (str, pos + 1); 
            if (pos < 0) 
                break;
            contador++;
        }
        return contador;
    }

    public static void abrirConta(){
        Conta novaConta = new Conta();
        Scanner input = new Scanner(System.in);
        System.out.print("Precisaremos de algumas informações pessoais\nNome: ");
        novaConta.setNome(input.nextLine()); 
        System.out.print("Cpf: ");
        novaConta.setCpf(input.next()); 
        System.out.print("Email: ");
        novaConta.setEmail(input.next()); 
        System.out.print("Telefone: ");
        novaConta.setTelefone(input.next()); 
        System.out.print("Código do banco: ");
        novaConta.setCodeBanco(input.next());
        System.out.print("Código da agência: ");
        novaConta.setCodeAgencia(input.next());
        novaConta.setNumeroConta(gerarNumeroConta()); //chama uma função gerarNumeroConta que vai ser responsavel por gerar um codigo númerico aleatorio de 7 dígitos
        System.out.print("Tipo da conta (tecle 7 para corrente e 9 para poupança): ");
        novaConta.setTipoConta(input.next());
        input.close();
        novaConta.setSaldo(0f); 
        novaConta.setExtrato("Saldo: R$" + novaConta.getSaldo());
        listaConta.add(novaConta);
        ManipularArquivo.salvar(listaConta);
        System.out.println("Conta aberta com sucesso!");
    }

    public static String gerarNumeroConta(){
        String numeroContaGerado;
        int temporaria = 0;
        while (true){
            numeroContaGerado = gerarCodigoAleatorio("0123456789", (byte) 7); //gerar um código que envolva qualquer numero entre 0 e 9 e que tenha 7 dígitos. *necessario o casting pra especificar o tipo
            for(int i=0; i < listaConta.size(); i++){
                if (numeroContaGerado.equals(listaConta.get(i).getNumeroConta())) //se o numero gerado já existir...
                    temporaria = 1; 
            }
            if (temporaria==0) //se temporaria for 0, então o numero gerado nao existe no banco de dados, e o programa pode sair do while e prosseguir
                break;
        }
        return numeroContaGerado; 
    }

    public static String gerarCodigoAleatorio(String str, byte tamanhoCodigo){  //a string str é composta por todas letras e/ou caracteres que o código gerado poderá ter 
        Random rand = new Random();
        String codigoAleatorio = "";
        int index = -1;
        for( int i = 0; i < tamanhoCodigo; i++ ) {
            index = rand.nextInt( str.length() );
            codigoAleatorio += str.substring( index, index + 1 );
        }
        return codigoAleatorio;
    }

    public static void abrirMenu(Conta conta){
        Scanner input = new Scanner(System.in);
        System.out.println("\n|Dados da conta|");
        System.out.println("Nome: " + conta.getNome() + "\tBanco: " + conta.getCodeBanco() + "\tAgencia: " + conta.getCodeAgencia() +
                           "\tTipo de conta: " + conta.getTipoConta() + "\tConta: " + conta.getNumeroConta() + "\n");
        byte opcao;
        byte condicao = 0;
        while (condicao == 0){
            System.out.println("|Menu|");
            System.out.print("1-Transferência\t\t2-Ver extrato\t\t3-Consultar saldo\t\t4-Depositar\t\t5-Sacar\t\t6-Deslogar\n\nDigite uma opção: ");
            opcao = input.nextByte();
            if (opcao==1)
                realizarTransferencia(conta);
            else if (opcao == 2)
                conta.exibirExtrato();
            else if (opcao == 3)
                conta.exibirSaldo();
            else if (opcao == 4)
                realizarDeposito(conta);
            else if (opcao == 5)
                realizarSaque(conta);
            else if (opcao == 6)
                break;
            else
                System.out.println("Opção inválida. Digite novamente: ");
        }
    }

    public static void realizarDeposito(Conta conta){
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o valor a ser depositado: ");
        float valor = input.nextFloat();
        conta.depositar(valor);
        ManipularArquivo.salvar(listaConta);
    }

    public static void realizarSaque(Conta conta){
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o valor a ser sacado: ");
        float valor = input.nextFloat();
        conta.sacar(valor);
        ManipularArquivo.salvar(listaConta);
    }

    public static void realizarTransferencia(Conta conta){
        Scanner input = new Scanner(System.in);
        System.out.print("Dados da conta de destino:\n\t\tCodigo do banco: ");
        String banco = input.next();
        System.out.print("\t\tNumero da agência: ");
        String agencia = input.next();
        System.out.print("\t\tTecle 7 para conta Corrente ou 9 para conta Poupança: ");
        String tipoConta = input.next();
        System.out.print("\t\tNumero da conta de destino: ");
        String numeroConta = input.next();
        System.out.print("Digite o valor: R$");
        float valor = input.nextFloat(); 
        String opcao;

        int index = -1;
        for (int i=0; i < listaConta.size(); i++){
            if (numeroConta.equals(listaConta.get(i).getNumeroConta()))
                index = i;
        }

        if(index == -1)
            System.out.println("\nConta inexistente!\n");    

        else if (banco.equals(listaConta.get(index).getCodeBanco()) && agencia.equals(listaConta.get(index).getCodeAgencia()) &&
                tipoConta.equals(listaConta.get(index).getTipoConta()) ){
                System.out.println("\nInformações da conta de destino:" +
                                    "\n\tNome: " + listaConta.get(index).getNome() + 
                                    "\n\tCpf: " + listaConta.get(index).getCpf() +
                                    "\n\tNúmero da conta: " + listaConta.get(index).getNumeroConta());
                System.out.print("\nDigite sua chave PIX para confirmar a transferência ou tecle 1 para cancelar: ");

                opcao = input.next();
                if ( opcao.equals(conta.getCpf()) || opcao.equals(conta.getTelefone()) || opcao.equals(conta.getEmail()) )
                    conta.transferir(conta,listaConta.get(index),valor);
                else if (opcao.equals("1"))
                    ;
                else
                    System.out.println("Chave PIX incorreta! Transferência encerrada.\n");
        }else
            System.out.println("\nDados incorretos!\n");    
        ManipularArquivo.salvar(listaConta);
    }
}

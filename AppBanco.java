import java.util.ArrayList;
import java.util.Scanner;
import java.io.RandomAccessFile;
import java.util.Random;

public class AppBanco{
    public static final String FILENAME = "/E:/UFS/3 PERIODO/POO/Projeto Pix/BaseClientes.txt"; //localizacao do arquivo de banco de dados
    private static ArrayList<Conta> listaConta = new ArrayList<>(); //arraylist onde será armazenado todos os objetos conta
    public static void main(String[] args) {
        //Extrair informações do arquivo FILENAME
        String dados = FILENAME; //caminho
        String textoArquivo = ManipularArquivo.ler(dados); //ler o arquivo todo como uma única string

        int qtdContas = encontrarQtdOcorrencias(textoArquivo,"/"); //encontrar o numero de contas baseado na quantidade de "/", ja que ao final de cada linha existe esse caracter
        
        String pessoa[] = new String[qtdContas]; //cada variavel pessoa armazenará uma linha do arquivo. Cada linha corresponde à informacoes de uma pessoa
        Conta[] conta = new Conta[qtdContas];  //criação dos objetos contas

        //inicializando cada posição do vetor conta como um objeto do tipo Conta
        for (int i=0; i<qtdContas; i++ )
            conta[i] = new Conta();

        //cada posição de pessoa[] gardará uma linha, ou seja, cada posição corresponde ás informações de uma pessoa
        for (int i=0; i<pessoa.length; i++ )
            pessoa[i] = textoArquivo.split("/")[i]; //quebrando o arquivo original em padrões, limitados pelo caracter "/". Cada linha finaliza no caracter "/"
        
        //O método ler irá quebrar novamente a string pessoa no padrão do caracter ";", alocando cada informacao ao seu respectivo atributo da conta
        for (int i=0; i<pessoa.length; i++ )
            listaConta.add(ler(conta[i],pessoa[i])); 

        String opcao;
        int index = -1;
        Scanner input = new Scanner(System.in);

        System.out.println("===========================================================================BANCO NEXTOLL===========================================================================");
        //byte condicao = 0;
        while(true){
            System.out.print("\n|Tela de Login|\n\n1-Logar\t\t2-Criar conta\t\t3-Sair\n\nEscolha uma opcao: ");
            opcao = input.next();
            if (opcao.equals("3")){
                salvar();
                break;
            }else if (opcao.equals("1")){
                System.out.print("\nCpf: ");
                opcao = input.next();
                for (int i=0; i < listaConta.size(); i++){
                    if(listaConta.get(i).getCpf().equals(opcao)) 
                        index = i;
                }
                if (index == -1)
                    System.out.println("\nCpf inválido!");
                else
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

    public static Conta ler(Conta conta, String pessoa){
        //pegar dados da string pessoa e armazenar no objeto conta
        conta.setNome(pessoa.split(";")[0]); 
        conta.setCpf(pessoa.split(";")[1]);    
        conta.setEmail(pessoa.split(";")[2]);    
        conta.setTelefone(pessoa.split(";")[3]);    
        conta.setCodeBanco(pessoa.split(";")[4]);    
        conta.setCodeAgencia(pessoa.split(";")[5]);    
        conta.setNumeroConta(pessoa.split(";")[6]);
        conta.setTipoConta(pessoa.split(";")[7]);
        conta.setSaldo(Float.valueOf(pessoa.split(";")[8]));   //conversao de String saldo para Float saldo, para facilitar seu uso interno no programa
        conta.setExtrato(pessoa.split(";")[9]);
        return conta;
    }

    public static void salvar(){
        try{
            RandomAccessFile arquivo = new RandomAccessFile(FILENAME, "rw");
            for (Conta conta : listaConta) {
                String linha = conta.getNome() + ";" + conta.getCpf() + ";" +
                                conta.getEmail() + ";" + conta.getTelefone() + ";" +
                                conta.getCodeBanco() + ";" + conta.getCodeAgencia() + ";" +
                                conta.getNumeroConta() + ";" + conta.getTipoConta() + ";" +
                                conta.getSaldo() + ";" + conta.getExtrato() + "/\n";
                arquivo.write(linha.getBytes());
            }
            arquivo.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
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
        novaConta.setSaldo(0f); 
        novaConta.setExtrato("Saldo: R$" + novaConta.getSaldo());
        listaConta.add(novaConta);
        salvar();
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
        salvar();
    }

    public static void realizarSaque(Conta conta){
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o valor a ser sacado: ");
        float valor = input.nextFloat();
        conta.sacar(valor);
        salvar();
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
        salvar();
    }
}

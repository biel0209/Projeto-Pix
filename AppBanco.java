import java.util.Scanner;
public class AppBanco{
    public static void main(String[] args){

        //ler nomes
        String dadosNomes = "/E:/UFS/3 PERIODO/POO/Projeto Pix/Nomes.txt"; //caminho
        String textoNomes = ManipularArquivo.ler(dadosNomes); //ler
        String nome[] = new String[3]; //criar vetor para armazenar os nomes
        nome[0] = textoNomes.split(";")[0];    
        nome[1] = textoNomes.split(";")[1];    
        nome[2] = textoNomes.split(";")[2];    

        //ler cpfs
        String dadosCpfs = "/E:/UFS/3 PERIODO/POO/Projeto Pix/Cpfs.txt"; //caminho
        String textoCpfs = ManipularArquivo.ler(dadosCpfs); //ler
        String cpf[] = new String[3]; //criar vetor para armazenar os cpfs
        cpf[0] = textoCpfs.split(";")[0];    
        cpf[1] = textoCpfs.split(";")[1];    
        cpf[2] = textoCpfs.split(";")[2];

        //ler emails
        String dadosEmails = "/E:/UFS/3 PERIODO/POO/Projeto Pix/Emails.txt"; //caminho
        String textoEmails = ManipularArquivo.ler(dadosEmails); //ler
        String email[] = new String[3]; //criar vetor para armazenar os emails
        email[0] = textoEmails.split(";")[0];    
        email[1] = textoEmails.split(";")[1];    
        email[2] = textoEmails.split(";")[2];

        //ler telefones
        String dadosTelefones = "/E:/UFS/3 PERIODO/POO/Projeto Pix/Telefones.txt"; //caminho
        String textoTelefones = ManipularArquivo.ler(dadosTelefones); //ler
        String telefone[] = new String[3]; //criar vetor para armazenar os telefones
        telefone[0] = textoTelefones.split(";")[0];    
        telefone[1] = textoTelefones.split(";")[1];    
        telefone[2] = textoTelefones.split(";")[2];

        //ler codigosBanco
        String dadosCodigosBanco = "/E:/UFS/3 PERIODO/POO/Projeto Pix/CodigosBanco.txt"; //caminho
        String textoCodigosBanco = ManipularArquivo.ler(dadosCodigosBanco); //ler
        String codigosBanco[] = new String[3]; //criar vetor para armazenar os codigos dos bancos
        codigosBanco[0] = textoCodigosBanco.split(";")[0];    
        codigosBanco[1] = textoCodigosBanco.split(";")[1];    
        codigosBanco[2] = textoCodigosBanco.split(";")[2];

        

        //ler codigosAgencia
        String dadosCodigosAgencia = "/E:/UFS/3 PERIODO/POO/Projeto Pix/CodigosAgencia.txt"; //caminho
        String textoCodigosAgencia = ManipularArquivo.ler(dadosCodigosAgencia); //ler
        String codigosAgencia[] = new String[3]; //criar vetor para armazenar os codigos das agencias
        codigosAgencia[0] = textoCodigosAgencia.split(";")[0];    
        codigosAgencia[1] = textoCodigosAgencia.split(";")[1];    
        codigosAgencia[2] = textoCodigosAgencia.split(";")[2];
        

        //ler numeros das contas
        String dadosNumeroContas = "/E:/UFS/3 PERIODO/POO/Projeto Pix/NumeroContas.txt"; //caminho
        String textoNumeroContas = ManipularArquivo.ler(dadosNumeroContas); //ler
        String numeroConta[] = new String[3]; //criar vetor para armazenar os numeros das contas
        numeroConta[0] = textoNumeroContas.split(";")[0];    
        numeroConta[1] = textoNumeroContas.split(";")[1];    
        numeroConta[2] = textoNumeroContas.split(";")[2];

         //ler tipo de conta
         String dadosTipoContas = "/E:/UFS/3 PERIODO/POO/Projeto Pix/TipoContas.txt"; //caminho
         String textoTipoContas = ManipularArquivo.ler(dadosTipoContas); //ler
         String tipoConta[] = new String[3]; //criar vetor para armazenar os tipos das contas
         tipoConta[0] = textoTipoContas.split(";")[0];    
         tipoConta[1] = textoTipoContas.split(";")[1];    
         tipoConta[2] = textoTipoContas.split(";")[2];

         //ler saldo
         String dadosSaldos = "/E:/UFS/3 PERIODO/POO/Projeto Pix/Saldos.txt"; //caminho
         String textoSaldos = ManipularArquivo.ler(dadosSaldos); //ler
         float saldo[] = new float[3]; //criar vetor para armazenar os saldos
         saldo[0] = Float.valueOf(textoSaldos.split(";")[0]); //convertendo a string pra float
         saldo[1] = Float.valueOf(textoSaldos.split(";")[1]); //convertendo a string pra float
         saldo[2] = Float.valueOf(textoSaldos.split(";")[2]); //convertendo a string pra float

        Conta conta0 = new Conta (nome[0],cpf[0],email[0],telefone[0],codigosBanco[0],
                                   codigosAgencia[0],numeroConta[0],tipoConta[0],saldo[0]);

        Conta conta1 = new Conta (nome[1],cpf[1],email[1],telefone[1],codigosBanco[1],
                                  codigosAgencia[1],numeroConta[1],tipoConta[1],saldo[1]);

        Conta conta2 = new Conta (nome[2],cpf[2],email[2],telefone[2],codigosBanco[2],
                                  codigosAgencia[2],numeroConta[2],tipoConta[2],saldo[2]);     

        String numeroDaConta;
        Scanner input = new Scanner(System.in);

        inicializarExtrato(conta0);
        inicializarExtrato(conta1);
        inicializarExtrato(conta2);

        System.out.println("===========================================================================BANCO NEXTOLL===========================================================================");
        byte condicao = 0;
        while(condicao == 0){
            System.out.print("\n|Tela de Login|\nDigite sua conta ou tecle 1 para sair: ");
            numeroDaConta = input.next();
            if (numeroDaConta.equals("5584917")){
                abrirMenu(conta0,conta1,conta2);
            }
            else if(numeroDaConta.equals("7134580")){
                abrirMenu(conta1,conta0,conta2);
            }
            else if(numeroDaConta.equals("2465879")){
                abrirMenu(conta2,conta0,conta1);
            }
            else if(numeroDaConta.equals("1")){
                break;
            }
        }
        input.close();
    }

    public static void inicializarExtrato(Conta conta){
        conta.setExtrato("Saldo: R$" + conta.getSaldo());
    }

    public static void abrirMenu(Conta conta0, Conta conta1, Conta conta2){
        Scanner input = new Scanner(System.in);
        System.out.println("\n|Dados da conta|");
        System.out.println("Nome: " + conta0.getNome() + "\tBanco: " + conta0.getCodeBanco() + "\tAgencia: " + conta0.getCodeAgencia() +
                           "\tTipo de conta: " + conta0.getTipoConta() + "\tConta: " + conta0.getNumeroConta() + "\n");
        byte opcao;
        byte condicao = 0;
        while (condicao == 0){
            System.out.println("|Menu|");
            System.out.print("1-Transferência\t\t2-Ver extrato\t\t3-Consultar saldo\t\t4-Depositar\t\t5-Sacar\t\t6-Deslogar\n\nDigite uma opção: ");
            opcao = input.nextByte();
            if (opcao==1){
                realizarTransferencia(conta0,conta1,conta2);
            }
            else if (opcao == 2){
                conta0.exibirExtrato();
            }
            else if (opcao == 3){
                conta0.exibirSaldo();
            }
            else if (opcao == 4){
                realizarDeposito(conta0);
            }
            else if (opcao == 5){
                realizarSaque(conta0);
            }
            else if (opcao == 6){
                break;
            }
            else
                System.out.println("Opção inválida. Digite novamente: ");
        }

    }

    public static void realizarDeposito(Conta conta0){
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o valor a ser depositado: ");
        float valor = input.nextFloat();
        conta0.depositar(valor);
    }

    public static void realizarSaque(Conta conta0){
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o valor a ser sacado: ");
        float valor = input.nextFloat();
        conta0.sacar(valor);
    }

    public static void realizarTransferencia(Conta conta0, Conta conta1, Conta conta2){
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
        if (banco.equals(conta1.getCodeBanco()) && agencia.equals(conta1.getCodeAgencia()) &&
                         tipoConta.equals(conta1.getTipoConta()) && numeroConta.equals(conta1.getNumeroConta())){
            System.out.println("\nInformações da conta de destino:" +
                               "\n\tNome: " + conta1.getNome() + 
                               "\n\tCpf: " + conta1.getCpf() +
                               "\n\tNúmero da conta: " + conta1.getNumeroConta());
            System.out.print("\nDigite sua chave PIX para confirmar a transferência ou tecle 1 para cancelar: ");

            opcao = input.next();
            if (opcao.equals(conta0.getTelefone()) || opcao.equals(conta0.getEmail()))
                conta0.transferir(conta0,conta1,valor);
            else if (opcao.equals("1"))
                ;
            else
                System.out.println("Chave PIX incorreta! Transferência encerrada.\n");
        }
        else if (banco.equals(conta2.getCodeBanco()) && agencia.equals(conta2.getCodeAgencia()) &&
                 tipoConta.equals(conta2.getTipoConta()) && numeroConta.equals(conta2.getNumeroConta())){
            System.out.println("\nDados da conta de destino:" +
                               "\n\tNome: " + conta2.getNome() + 
                               "\n\tCpf: " + conta2.getCpf() +
                               "\n\tNúmero da conta: " + conta2.getNumeroConta());
            System.out.print("\nDigite sua chave PIX para confirmar a transferência ou tecle 1 para cancelar: ");

            opcao = input.next();
            if (opcao.equals(conta0.getTelefone()) || opcao.equals(conta0.getEmail()))
                conta0.transferir(conta0,conta1,valor);
            else if (opcao.equals("1"))
                ;
            else
                System.out.println("Chave PIX incorreta! Transferência encerrada.\n");    
        }
        else
            System.out.println("\nConta inexistente ou dados incorretos!\n");    


    }

}

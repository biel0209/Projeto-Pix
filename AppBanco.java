import java.util.Scanner;
public class AppBanco{
    public static void main(String[] args){
        Conta conta1 = new Conta ("Jose","03549135177","joseberson45@gmail.com","998546621","108",
                                   "1733","5584917","7",4500f);
        Conta conta2 = new Conta ("Marcus","12304587944","marcus.bg@hotmail.com","988456517","108",
                                   "1733","7134580","9",5000f);
        Conta conta3 = new Conta ("Andre","54691532105","andrelg@gmail.com","999546278","108",
                                   "1733","2465879","7",2300f);     

        String numeroDaConta;
        Scanner input = new Scanner(System.in);

        inicializarExtrato(conta1);
        inicializarExtrato(conta2);
        inicializarExtrato(conta3);

        System.out.println("===========================================================================BANCO NEXTOLL===========================================================================");
        byte condicao = 0;
        while(condicao == 0){
            System.out.print("\n|Tela de Login|\nDigite sua conta ou tecle 1 para sair: ");
            numeroDaConta = input.next();
            if (numeroDaConta.equals("5584917")){
                abrirMenu(conta1,conta2,conta3);
            }
            else if(numeroDaConta.equals("7134580")){
                abrirMenu(conta2,conta1,conta3);
            }
            else if(numeroDaConta.equals("2465879")){
                abrirMenu(conta3,conta1,conta2);
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

    public static void abrirMenu(Conta conta1, Conta conta2, Conta conta3){
        Scanner input = new Scanner(System.in);
        System.out.println("\n|Dados da conta|");
        System.out.println("Nome: " + conta1.getNome() + "\tBanco: " + conta1.getCodeBanco() + "\tAgencia: " + conta1.getCodeAgencia() +
                           "\tTipo de conta: " + conta1.getTipoConta() + "\tConta: " + conta1.getNumeroConta() + "\n");
        byte opcao;
        byte condicao = 0;
        while (condicao == 0){
            System.out.println("|Menu|");
            System.out.print("1-Transferência\t\t2-Ver extrato\t\t3-Consultar saldo\t\t4-Deslogar\n\nDigite uma opção: ");
            opcao = input.nextByte();
            if (opcao==1){
                realizarTransferencia(conta1,conta2,conta3);
            }
            else if (opcao == 2){
                conta1.exibirExtrato();
            }
            else if (opcao == 3){
                conta1.exibirSaldo();
            }
            else if (opcao == 4){
                break;
            }
            else
                System.out.println("Opção inválida. Digite novamente: ");
        }

    }

    public static void realizarTransferencia(Conta conta1, Conta conta2, Conta conta3){
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
        if (banco.equals(conta2.getCodeBanco()) && agencia.equals(conta2.getCodeAgencia()) &&
                         tipoConta.equals(conta2.getTipoConta()) && numeroConta.equals(conta2.getNumeroConta())){
            System.out.println("\nInformações da conta de destino:" +
                               "\n\tNome: " + conta2.getNome() + 
                               "\n\tCpf: " + conta2.getCpf() +
                               "\n\tNúmero da conta: " + conta2.getNumeroConta());
            System.out.print("\nDigite sua chave PIX para confirmar a transferência ou tecle 1 para cancelar: ");

            opcao = input.next();
            if (opcao.equals(conta1.getTelefone()) || opcao.equals(conta1.getEmail()))
                conta1.transferir(conta1,conta2,valor);
            else if (opcao.equals("1"))
                ;
            else
                System.out.println("Chave PIX incorreta! Transferência encerrada.\n");
        }
        else if (banco.equals(conta3.getCodeBanco()) && agencia.equals(conta3.getCodeAgencia()) &&
                 tipoConta.equals(conta3.getTipoConta()) && numeroConta.equals(conta3.getNumeroConta())){
            System.out.println("\nDados da conta de destino:" +
                               "\n\tNome: " + conta3.getNome() + 
                               "\n\tCpf: " + conta3.getCpf() +
                               "\n\tNúmero da conta: " + conta3.getNumeroConta());
            System.out.print("\nDigite sua chave PIX para confirmar a transferência ou tecle 1 para cancelar: ");

            opcao = input.next();
            if (opcao.equals(conta1.getTelefone()) || opcao.equals(conta1.getEmail()))
                conta1.transferir(conta1,conta2,valor);
            else if (opcao.equals("1"))
                ;
            else
                System.out.println("Chave PIX incorreta! Transferência encerrada.\n");    
        }
        else
            System.out.println("\nConta inexistente ou dados incorretos!\n");    


    }

}

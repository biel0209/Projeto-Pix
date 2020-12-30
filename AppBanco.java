import Conta;
public class AppBanco{
    public static void main(String[] args){
        Conta conta1 = new Conta ("Jose","03549135177","joseberson45@gmail.com","998546621","108",
                                   "1733","5584917","013",4500f);
        Conta conta2 = new Conta ("Marcus","12304587944","marcus.bg@hotmail.com","988456517","108",
                                   "1733","5584917","013",5000f);
        Conta conta3 = new Conta ("Andre","54691532105","andrelg@gmail.com","999546278","108",
                                   "1733","2465879","013",2300f);     
                                   
                                   
        System.out.println("Saldo da conta 1: " + conta1.getSaldo());
        System.out.println("Saldo da conta 3: " + conta3.getSaldo());

        System.out.println("Transferindo...");
        conta1.transferir(conta1, conta3, 150f);

        System.out.println("Saldo da conta 1: " + conta1.getSaldo());
        System.out.println("Saldo da conta 3: " + conta3.getSaldo());

    }
}
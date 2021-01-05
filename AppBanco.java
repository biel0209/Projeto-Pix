public class AppBanco{
    public static void main(String[] args){
        Conta conta1 = new Conta ("Jose","03549135177","joseberson45@gmail.com","998546621","108",
                                   "1733","5584917","013",4500f);
        Conta conta2 = new Conta ("Marcus","12304587944","marcus.bg@hotmail.com","988456517","108",
                                   "1733","5584917","013",5000f);
        Conta conta3 = new Conta ("Andre","54691532105","andrelg@gmail.com","999546278","108",
                                   "1733","2465879","013",2300f);     

        inicializarExtrato(conta1);
        inicializarExtrato(conta2);
        inicializarExtrato(conta3);


        conta1.transferir(conta1, conta3, 150f);

        conta2.transferir(conta2, conta1, 500f);

        conta1.transferir(conta1, conta2, 2500f);

        conta1.exibirSaldo();
        conta2.exibirSaldo();
        conta3.exibirSaldo();

        conta3.transferir(conta3, conta2, 1500f);

        conta3.transferir(conta3, conta1, 1280f);


        conta1.exibirExtrato();
        conta2.exibirExtrato(); 
        conta3.exibirExtrato();

    }

    public static void inicializarExtrato(Conta conta){
        conta.setExtrato("Saldo: " + conta.getSaldo());
    }

}

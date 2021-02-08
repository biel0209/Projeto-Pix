public class Conta{
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String codeBanco;
    private String codeAgencia;
    private String numeroConta;
    private String tipoConta;
    private float saldo;
    private String extrato;
    private String pix;

    public Conta(){}

    public Conta (String nome, String cpf, 
                 String email, String telefone,
                 String codeBanco, String codeAgencia, 
                 String numeroConta, String tipoConta, 
                 float saldo){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.codeBanco = codeBanco;
        this.codeAgencia = codeAgencia;
        this.numeroConta = numeroConta;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
    }

    public void transferir(Conta origem, Conta destino, float valor){
        if (valor <= origem.getSaldo()){
            origem.setSaldo(origem.getSaldo() - valor);
            destino.setSaldo(destino.getSaldo() + valor);

            System.out.println("\nTransferência de R$" + valor + " reais realizada com sucesso!\n");
           
            origem.setExtrato(origem.getExtrato() + " || Transferencia via PIX: " + "-R$" + valor );
            destino.setExtrato(destino.getExtrato() + " || Transferencia via PIX: " + "+R$" + valor );

            origem.setExtrato(origem.getExtrato() + " || Saldo: R$" + origem.getSaldo() );
            destino.setExtrato(destino.getExtrato() + " || Saldo: R$" + destino.getSaldo() );
        }
        else    
            System.out.println("Saldo insuficiente para transferir!\n");
    }

    public void exibirExtrato(){
        System.out.println("\n============Extrato===========\n" + 
                            "Conta: "+ getNumeroConta() + "\n\n" + getExtrato() + "\n");
    }

    public void exibirSaldo(){
        System.out.println("\n============Saldo===========\n" + 
                            "Conta: "+ getNumeroConta() + "\n\nR$" + getSaldo() + "\n");
    }

    public void depositar(float valor){
        setSaldo(getSaldo() + valor);
        setExtrato(getExtrato() + " || Deposito: " + "+R$" + valor);
        setExtrato(getExtrato() + " || Saldo: R$" + getSaldo());
        System.out.println("Deposito de R$" + valor + " reais realizado com sucesso!\n");


    }

    public void sacar(float valor){
        if (valor <= getSaldo()){
            setSaldo(getSaldo() - valor);
            setExtrato(getExtrato() + " || Saque: " + "-R$" + valor);
            setExtrato(getExtrato() + " || Saldo: R$" + getSaldo());
            System.out.println("Saque de R$" + valor + " reais realizado com sucesso!\n");

        }else
            System.out.println("Saldo insuficiente para sacar!\n");

    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCodeBanco() {
        return this.codeBanco;
    }

    public void setCodeBanco(String codeBanco) {
        this.codeBanco = codeBanco;
    }

    public String getCodeAgencia() {
        return this.codeAgencia;
    }

    public void setCodeAgencia(String codeAgencia) {
        this.codeAgencia = codeAgencia;
    }

    public String getNumeroConta() {
        return this.numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getTipoConta() {
        return this.tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public float getSaldo() {
        return this.saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getExtrato() {
        return this.extrato;
    }

    public void setExtrato(String extrato) {
        this.extrato = extrato;
    }

    public String getPix() {
        return this.pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
    }

   

}


public class Agencia1 extends BancoA{
    private String code_agencia1 = "0780";

    public Agencia1(String code_bancoA, String code_agencia1){
        super(code_bancoA);
        this.code_agencia1 = code_agencia1;
    }

    public String getCode_agencia1() {return this.code_agencia1;}

    public void setCode_agencia1(String code_agencia1) {this.code_agencia1 = code_agencia1;}

    
}

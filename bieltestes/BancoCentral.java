import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BancoCentral {

    private String cpf;

    public BancoCentral(){
    }


    //Quebra de linha n funconando...
    public void gerarPix(){

        String carac = "abcdefghijklmnopqrstuvwxyz1234567890";
        char[] base = carac.toCharArray();
        String pix="";
        Random rnd = new Random();

        for (int i=0; i<20; i++ ){
            int ind = rnd.nextInt(35);
            pix=pix+base[ind];
        }
        
        try (
                BufferedReader buff = new BufferedReader(new FileReader("ListaPix.txt"));
                
                ){

            String linha="";
            while (linha!=null) {
                if (linha == pix){
                    gerarPix();

                } else {
            
                    linha = buff.readLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        ManipularArquivo.salvarPix("ListaPix.txt", (pix+"\n"));            
            
        
        System.out.print(pix);

        
    }


    




    public boolean validarEmail(String mail){
        String emailRegex = "^[A-Z0-9._-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(mail);
        return matcher.find();

    }



    public boolean validarCPF(String num){
        cpf = num;
        int soma1,soma2,verid1, verid2;
        
        char[] base = cpf.toCharArray();
        int d1=Character.getNumericValue(base[9]);
        int d2=Character.getNumericValue(base[10]);

        soma1=0;
        soma2=0;
        
        for (int i=0, j=10; i<9 && j>1; i++,j--){

            int n = Character.getNumericValue(base[i]);
            soma1 = soma1+(n*j);             
        }

        int teste1 = (11-(soma1%11));
        if (teste1 == 10 || teste1 == 11){
            verid1=0;
        } else {
            verid1 = teste1;
        }


        for (int i=0, j=11; i<10 && j>1; i++,j--){

            int m = Character.getNumericValue(base[i]);
            soma2 = soma2+(m*j);            
        }

        int teste2 = (11-(soma2%11));
        if (teste2 == 10 || teste2 == 11){
            verid2=0;
        } else {
            verid2 = teste2;
        }

        if (d1 == verid1 && d2 == verid2){
            return true;
        } else {
            return false;
        }

    }
    
}

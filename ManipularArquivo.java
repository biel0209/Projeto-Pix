import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import jdk.jfr.events.FileWriteEvent;

public class ManipularArquivo{
    public static String ler (String caminho){
        String conteudo = ""; 
        try {
            FileReader arquivo = new FileReader(caminho);
            BufferedReader lerArquivo = new BufferedReader(arquivo);
            String linha = "";
            try{
                linha = lerArquivo.readLine();
                while (linha != null){
                    conteudo += linha;
                    linha = lerArquivo.readLine();
                }
                arquivo.close();
                return conteudo;
            }catch (IOException exception){
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return "";
            }
        }catch (FileNotFoundException exception){
            System.out.println("Erro: Arquivo não encontrado!");
            return "";
        }
    }

    public static boolean escrever (String caminho, String texto){
        try {
            FileWriter arquivo = new FileWriter(caminho);
            PrintWriter gravarArquivo = new PrintWriter(arquivo);
            gravarArquivo.println(texto);
            gravarArquivo.close();
            return true;
        }catch (IOException exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }



}
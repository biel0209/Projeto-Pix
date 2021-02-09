package codigos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import jdk.jfr.events.FileWriteEvent;
import java.util.ArrayList;


public class ManipularArquivo{ //Classe responsável por todas as operações envolvendo o arquivo, como extrair suas informações (ler) e gravar os dados(salvar)
    public static final String CAMINHO = "/Projeto Pix/codigos/BaseClientes.txt"; //localizacao do arquivo de banco de dados

    public static String ler(){  //método responsável por ler o arquivo e devolver todo seu conteúdo como uma única String
        String conteudo = ""; 
        try {
            FileReader arquivo = new FileReader(CAMINHO);
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

    public static Conta setarDados(Conta conta, String pessoa){   //método responsável por atribuir cada tipo de informação ao atributo adequado da classe Conta
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
        conta.setPix(pessoa.split(";")[9]);
        conta.setExtrato(pessoa.split(";")[10]);
        return conta;
    }

    public static void salvar(ArrayList<Conta> listaConta){   //salva todas as informações contidas em ArrayList listaConta no arquivo 
        try{
            RandomAccessFile arquivo = new RandomAccessFile(CAMINHO, "rw");
            for (Conta conta : listaConta) {
                String linha = conta.getNome() + ";" + conta.getCpf() + ";" +
                                conta.getEmail() + ";" + conta.getTelefone() + ";" +
                                conta.getCodeBanco() + ";" + conta.getCodeAgencia() + ";" +
                                conta.getNumeroConta() + ";" + conta.getTipoConta() + ";" +
                                conta.getSaldo() + ";" + conta.getPix() + ";" + conta.getExtrato() + "/\n";
                arquivo.write(linha.getBytes());
            }
            arquivo.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

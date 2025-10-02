package util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// classe dedicada para testes simples
public class FuncoesUteis {

    public static void verificarCaminho(String caminhoAbsoluto){
        File arquivo = new File(caminhoAbsoluto);
        if (arquivo.exists()) {
            System.out.println("caminho encontrado");
            System.out.println(arquivo.getAbsolutePath());
        } else {
            System.out.println("caminho NÃO encontrado");
        }
    }



    public static String lerArquivoComoString(String caminho){
        try{
            return Files.readString(Paths.get(caminho));
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}

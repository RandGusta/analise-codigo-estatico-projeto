package util;
import java.io.File;

// classe dedicada para testes simples
public class FuncoesUteis {

    public void VerificarCaminho(String caminhoAbsoluto){
        File arquivo = new File(caminhoAbsoluto);
        if (arquivo.exists()) {
            System.out.println("caminho encontrado");
            System.out.println(arquivo.getAbsolutePath());
        } else {
            System.out.println("caminho NÃO encontrado");
        }
    }
}

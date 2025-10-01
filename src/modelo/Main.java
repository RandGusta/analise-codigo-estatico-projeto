package modelo;
import enums.TipoLinguagem;
import java.io.File;
import util.FuncoesUteis;
public class Main {
    public static void main(String[] args) {
        String caminhoAbsoluto = "C:/Users/gusta/OneDrive/Área de Trabalho/arquivoteste.txt";
        ArquivoCodigo arquivoCodigo = new ArquivoCodigo("teste.js", TipoLinguagem.JAVASCRIPT);
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        funcoesUteis.VerificarCaminho(caminhoAbsoluto);

    }
}



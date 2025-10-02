package modelo;
import enums.TipoLinguagem;
import java.io.File;
import util.FuncoesUteis.*;

import static util.FuncoesUteis.verificarCaminho;
import static util.FuncoesUteis.lerArquivoComoString;

public class Main {
    public static void main(String[] args) {
        String caminhoAbsoluto = "C:/Users/gusta/OneDrive/Área de Trabalho/arquivoteste.txt";
        ArquivoCodigo arquivoCodigo = new ArquivoCodigo("teste.js", TipoLinguagem.JAVASCRIPT);

        String caminhoString = lerArquivoComoString(caminhoAbsoluto);
        RegraAnalise regraAnalise = new RegraAnalise(5);
        int quantidadeParametros  = regraAnalise.verificarQuantidadeParametros(caminhoString);
        System.out.println(quantidadeParametros);
        Projeto projeto = new Projeto("teste", caminhoAbsoluto, arquivoCodigo);


    }
}



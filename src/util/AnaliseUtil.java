package util;

import modelo.enums.comentariosCodigo.ComentarioJS;
import modelo.enums.estruturasDecisao.EstruturaDecisaoJs;
import modelo.enums.tipoDeclaracao.TipoDeclaracaoJS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnaliseUtil {



    public static Matcher obterFuncoes(String codigo, TipoDeclaracaoJS tipo) {
        Pattern padrao = tipo.getPadrao();
        return padrao.matcher(codigo);
    }

    public static Matcher obterComentario(String conteudo, ComentarioJS comentarioJS){
        Pattern pattern = comentarioJS.getPadrao();
        return pattern.matcher(conteudo);
    }

    public static Matcher obterEstruturaDecisao(String codigo, EstruturaDecisaoJs estruturaDecisaoJs){
        Pattern pattern = estruturaDecisaoJs.getPadrao();
        return pattern.matcher(codigo);
    }



    public static int contarParametros(String parametros) {
        if (parametros == null || parametros.isBlank()) return 0;
        String[] vetor = parametros.split(",");
        int cont = 0;
        for (String p : vetor) {
            if (!p.trim().isEmpty()) cont++;
        }
        return cont;
    }


    public static int calcularLinha(String codigo, int posicao) {
        if (posicao == 0) return 1;
        String parteAnterior = codigo.substring(0, posicao);
        return parteAnterior.length() - parteAnterior.replace("\n", "").length() + 1;
    }
}
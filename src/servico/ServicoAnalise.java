package servico;

import modelo.TipoDeclaracao;
import modelo.TipoProblema;
import modelo.tipoDeclaracao.TipoDeclaracaoJS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServicoAnalise {

    // Retorna um matcher para todas as funções do código JS
    public Matcher obterFuncoes(String codigo, TipoDeclaracaoJS tipo) {
        Pattern padrao = tipo.getPadrao();
        return padrao.matcher(codigo);
    }


    public int contarParametros(String parametros) {
        if (parametros == null || parametros.isBlank()) return 0;
        String[] vetor = parametros.split(",");
        int cont = 0;
        for (String p : vetor) {
            if (!p.trim().isEmpty()) cont++;
        }
        return cont;
    }


    public int contarLinhas(String codigo) {
        return codigo.split("\r?\n").length;
    }

    // Pega o nome da função se ela for do tipo tradicional
    public String extrairNome(Matcher matcher, TipoDeclaracaoJS tipo) {
        return tipo.extrairNome(matcher);
    }
}


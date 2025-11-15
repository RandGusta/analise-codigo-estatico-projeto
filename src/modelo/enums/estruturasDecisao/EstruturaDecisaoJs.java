package modelo.enums.estruturasDecisao;

import java.util.regex.Pattern;

public enum EstruturaDecisaoJs {
    ESTRUTURA_DECISAO_JS("\\b(if|for|while|case|catch)\\b|(\\?|\\&\\&|\\|\\|)");

    private final Pattern padrao;


    EstruturaDecisaoJs(String padrao)
    {
        this.padrao = Pattern.compile(padrao);

    }

    public Pattern getPadrao() {
        return padrao;
    }


}

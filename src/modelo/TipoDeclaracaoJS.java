package modelo;

import java.util.regex.Pattern;

public enum TipoDeclaracaoJS implements TipoDeclaracao{
    DECLARACAO_TRADICIONAL(Pattern.compile("function\\s+([a-zA-Z0-9_]+)\\s*\\((.*\\))")),
    ARROW_FUNCTION(Pattern.compile("const\\s+[a-zA-Z0-9_]+\\s*=\\s*\\(.*?\\)\\s*=>"));
    private final Pattern padrao;

    TipoDeclaracaoJS(Pattern padrao){
        this.padrao = padrao;
    }


    @Override
    public Pattern getPadrao(){
        return padrao;
    }


}

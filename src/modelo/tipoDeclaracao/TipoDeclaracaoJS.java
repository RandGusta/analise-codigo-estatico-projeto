package modelo.tipoDeclaracao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TipoDeclaracaoJS {
    DECLARACAO_TRADICIONAL("function\\s+(\\w+)\\s*\\((.*?)\\)", 1, 2),
    ARROW_FUNCTION("const\\s+(\\w+)\\s*=\\s*\\((.*?)\\)\\s*=>", 1, 2),
    FUNCAO_ANONIMA("const\\s+\\w+\\s*=\\s*function\\s*\\((.*?)\\)", -1, 1);

    private final Pattern padrao;
    private final int grupoNome;
    private final int grupoParametros;

    TipoDeclaracaoJS(String regex, int grupoNome, int grupoParametros) {
        this.padrao = Pattern.compile(regex);
        this.grupoNome = grupoNome;
        this.grupoParametros = grupoParametros;
    }

    public Pattern getPadrao() { return padrao; }

    public String extrairNome(Matcher matcher) {
        if (grupoNome == -1) return "anonima";
        return matcher.group(grupoNome);
    }

    public String extrairParametros(Matcher matcher) {
        return matcher.group(grupoParametros);
    }
}

package modelo.enums.comentariosCodigo;

import java.util.regex.Pattern;

public enum ComentarioJS {
    COMENTARIO_PADRAO("//.*"),
    COMENTARIO_BLOCO("/\\*[\\s\\S]*?\\*/"),
    FIM_BLOCO("\\*/");
    private final Pattern padrao;


    ComentarioJS(String padrao){
        this.padrao = Pattern.compile(padrao);
    }


    public Pattern getPadrao() {
        return padrao;
    }
}

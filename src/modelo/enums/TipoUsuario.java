package modelo.enums;

public enum TipoUsuario {
    ADM("Administrador"),
    PADRAO("Usuário padrão");


    private final String descricao;

    TipoUsuario(String descricao){
        this.descricao = descricao;
    }


    public String getDescricao(){
        return descricao;
    }

}

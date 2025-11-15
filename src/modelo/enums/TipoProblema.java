package modelo.enums;

public enum TipoProblema {
    QTD_PARAMETROS_EXECIDA("001", "Função ultrapassa a quantidade de parametros recomendada"),
    NOME_FUNCAO_LONGO("002", "Nome da função excede tamanho recomendado"),
    FUNCAO_SEM_COMENTARIO("003", "Função não possui comentários");

    private final String codigo;
    private final String descricao;


    // construtor privado que a própria constante chama para construir sues valores
    TipoProblema(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }


    public String getCodigo(){
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}

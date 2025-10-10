package modelo;

public class Ocorrencia {
    private String nomeArquivo;
    private int linha;
    private String tipoProblema;
    private String descricao;

    public Ocorrencia(String tipoProblema, String nomeArquivo, int linha, String descricao){
        this.tipoProblema = tipoProblema;
        this.nomeArquivo = nomeArquivo;
        this.linha = linha;
        this.descricao = descricao;
    }

    public int getLinha() {
        return linha;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipoProblema() {
        return tipoProblema;
    }
}

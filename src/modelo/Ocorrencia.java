package modelo;

public class Ocorrencia {
    private String nomeArquivo;
    private String nomeFuncao; // temporario
    private int linha;
    private String tipoProblema;
    private String descricao;

    public Ocorrencia(String tipoProblema, String nomeArquivo, int linha, String descricao, String nomeFuncao){
        this.tipoProblema = tipoProblema;
        this.nomeArquivo = nomeArquivo;
        this.linha = linha;
        this.descricao = descricao;
        this.nomeFuncao = nomeFuncao; // temporariamente
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

    public String ocorrenciaCompleta(){
         return String.format("""
                Nome do Arquivo:  %s
                Tipo do problema:  %s
                Descrição: %s
                Linha: %s
                Nome função: %s
                """, nomeArquivo, tipoProblema, descricao, linha, nomeFuncao);

    }
}

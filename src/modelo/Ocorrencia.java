package modelo;
import javax.persistence.*;

@Entity
@Table(name = "Ocorrencia")
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @Column(name = "nome_funcao")
    private String nomeFuncao; // temporario

    @Column(name = "linha")
    private int linha;

    @Column(name = "tipo_problema")
    private String tipoProblema;

    @Column(name = "descricao")
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

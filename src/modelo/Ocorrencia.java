package modelo;
import javax.persistence.*;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "arquivo_codigo_id")
    private ArquivoCodigo arquivoCodigo;

    @Column(name = "nome_funcao")
    private String nomeFuncao; // temporario

    @Column(name = "linha")
    private int linha;

    @Column(name = "tipo_problema")
    private String tipoProblema;

    @Column(name = "descricao")
    private String descricao;

    public Ocorrencia(String tipoProblema, ArquivoCodigo arquivoCodigo, int linha, String descricao, String nomeFuncao){
        this.tipoProblema = tipoProblema;
        this.arquivoCodigo = arquivoCodigo;
        this.linha = linha;
        this.descricao = descricao;
        this.nomeFuncao = nomeFuncao; // temporariamente
    }

    protected Ocorrencia(){}

    public int getLinha() {
        return linha;
    }

    public ArquivoCodigo getNomeArquivo() {
        return arquivoCodigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipoProblema() {
        return tipoProblema;
    }

    public String getNomeFuncao(){return nomeFuncao;}

    public String ocorrenciaCompleta(){
         return String.format("""
                Nome do Arquivo:  %s
                Tipo do problema:  %s
                Descrição: %s
                Linha: %s
                Nome função: %s
                """, arquivoCodigo.getNome(), tipoProblema, descricao, linha, nomeFuncao);

    }
}


package modelo;
import java.util.List;
import modelo.enums.TipoLinguagem;

import javax.persistence.*;

@Entity
@Table(name = "arquivo_codigo")
public class ArquivoCodigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoLinguagem tipoLinguagem;
    @Lob
    @Column(name = "conteudo", columnDefinition = "LONGTEXT")
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "id_projeto")
    private Projeto projeto;

    @OneToMany(mappedBy = "arquivoCodigo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ocorrencia> ocorrencias;

    public ArquivoCodigo(String nome, TipoLinguagem tipoLinguagem, String conteudo){
        this.nome = nome;
        this.tipoLinguagem = tipoLinguagem;
        this.conteudo = conteudo;
    }

    protected ArquivoCodigo(){}

    public String getConteudo(){
        return this.conteudo;
    }

    public String getNome() {
        return nome;
    }

    public List<Ocorrencia> getOcorrencias(){
        return this.ocorrencias;
    }


    public void setProjeto(Projeto projeto){
        this.projeto = projeto;
    }

    public TipoLinguagem getTipoLinguagem() {
        return tipoLinguagem;
    }

    public Long getId() {
        return id;
    }



    @Override
    public String toString() {
        String string = String.format("Nome: %s, Linguagem: %s, Caminho do Arquivo: %s", this.nome, this.ocorrencias);
        return string;
    }
}

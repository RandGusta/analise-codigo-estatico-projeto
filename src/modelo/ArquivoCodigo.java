
package modelo;
import java.util.List;
import enums.TipoLinguagem;

import javax.persistence.*;

@Entity
@Table(name = "arquivo_codigo")
public class ArquivoCodigo {

    @Column(name = "nome")
    private String nome;
    @Column(name = "tipo_linguagem")
    private TipoLinguagem tipoLinguagem;
    @Column(name = "conteudo")
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "id_projeto")
    private Projeto projeto;

    @OneToMany(mappedBy = "arquivo_codigo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ocorrencia> ocorrencias;

    @Column(name="caminho")
    private String caminho;

    public ArquivoCodigo(String nome, TipoLinguagem tipoLinguagem, String conteudo, String caminho){
        this.nome = nome;
        this.tipoLinguagem = tipoLinguagem;
        this.conteudo = conteudo;
        this.caminho = caminho;
    }

    public String getConteudo(){
        return this.conteudo;
    }

    public String getNome() {
        return nome;
    }

    public List<Ocorrencia> getOcorrencias(){
        return this.ocorrencias;
    }

    public void atualizarCaminho(String novoCaminho){
        this.caminho = novoCaminho;
        System.out.println("caminho atualizado");
    }

    @Override
    public String toString() {
        String string = String.format("Nome: %s, Linguagem: %s, Caminho do Arquivo: %s", this.nome, this.ocorrencias, this.caminho);
        return string;
    }
}

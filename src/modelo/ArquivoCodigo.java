
package modelo;
import java.util.List;
import enums.TipoLinguagem;

public class ArquivoCodigo {
    private String nome;
    private TipoLinguagem tipoLinguagem;
    private String conteudo;
    private List<Ocorrencia> ocorrencias;

    public ArquivoCodigo(String nome, TipoLinguagem tipoLinguagem, String conteudo){
        this.nome = nome;
        this.tipoLinguagem = tipoLinguagem;
        this.conteudo = conteudo;
    }

    public String getConteudo(){
        return this.conteudo;
    }

    public String getNome() {
        return nome;
    }
}

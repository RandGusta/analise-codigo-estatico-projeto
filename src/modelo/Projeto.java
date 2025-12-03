package modelo;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "projeto")
public class Projeto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name="nome_projeto", nullable = false)
        private String nomeProjeto;
        @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<ArquivoCodigo> arquivo;
        @ManyToOne
        @JoinColumn(name = "id_usuario")
        private Usuario usuario;


    public Projeto(String nomeProjeto, List<ArquivoCodigo> arquivo){
        this.nomeProjeto = nomeProjeto;
        this.arquivo = arquivo;
    }

    protected Projeto(){}

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public Long getId(){
        return id;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public List<ArquivoCodigo> getArquivos() {
        return arquivo;
    }
}

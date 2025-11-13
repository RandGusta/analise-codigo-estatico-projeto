package modelo;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "projeto")
public class Projeto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name="nome_projeto")
        private String nomeProjeto;
        @Column(name="caminho")
        private String caminho;
        @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<ArquivoCodigo> arquivo;


    public Projeto(String nomeProjeto, String caminho ,List<ArquivoCodigo> arquivo){
        this.nomeProjeto = nomeProjeto;
        this.caminho = caminho;
        this.arquivo = arquivo;
    }

        public void adicionarArquivo(ArquivoCodigo arquivoCodigo){
            if(arquivoCodigo != null){
                this.arquivo.add(arquivoCodigo);
            } else {
                System.err.println("Arquivo não existe para ser adicionado ao projeto");
            }
        }


        public void removerArquivo(ArquivoCodigo arquivoCodigo){
            if(this.arquivo.contains(arquivoCodigo)){
                this.arquivo.remove(arquivoCodigo);
                System.out.println("Arquivo Removido com secusso!!");
            } else {
                System.err.println("Arquivo não encontrado para ser removido");
            }

        }


        public void listarArquivos(){
        if(!this.arquivo.isEmpty()){
            for(ArquivoCodigo arq: this.arquivo){
                System.out.println(arq);
            }
        }



        }





}

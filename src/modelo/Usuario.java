package modelo;
import modelo.enums.TipoUsuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nome")
    private String nome;
    @Column(name ="email", nullable = false)
    private String email;
    @Column(name="senha", nullable = false)
    private String senha;
    @CreationTimestamp
    @Column(name = "data_cadastro",nullable = false, updatable = false)
    private LocalDateTime dataCadastro;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Projeto> projeto;


    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;

    }

    protected Usuario(){}

    public String getNome() {
        return nome;
    }


    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }


    public String getEmail() {
        return email;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public List<Projeto> getProjeto(){return projeto;}

    public String getSenha(){return senha;}

        public String usuarioDescricao(){
        String string =
                String.format("Nome usuário: %s, \n Email cadastrado: %s, \n Data registro %s", this.nome, this.email, this.dataCadastro);
        return string;
        }





}



package modelo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// classe com informações do usuário
public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataCadastro;
    private TipoUsuario tipoUsuario;


    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;

    }


    public String getNome() {
        return nome;
    }


    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }


    public String getEmail() {
        return email;
    }


}



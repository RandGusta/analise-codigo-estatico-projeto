package servico;

import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import modelo.Usuario;
import modelo.enums.TipoUsuario;

import javax.persistence.EntityManager;

public class ServicoUsuario {
    private UsuarioDAOImpl  usuarioDAO;


    public ServicoUsuario(UsuarioDAOImpl  usuarioDAO){
        this.usuarioDAO = usuarioDAO;
    }

    public void adicionarUsuario(String nome, String email, String senha){
        if(usuarioDAO.buscarUsuarioPorEmail(email) != null){
            throw new RuntimeException("Email já cadastrado");
        } else {
            TipoUsuario tipoUsuario = definirTipoUsuario();
            Usuario usuario = new Usuario(nome, email, senha, tipoUsuario);
            usuarioDAO.adicionarUsuario(usuario);
        }


    }


    private TipoUsuario definirTipoUsuario(){
        if(usuarioDAO.contarUsuariosCadastrados() == 0L){
            return TipoUsuario.ADM;
        } else {
            return TipoUsuario.PADRAO;
        }
    }

}

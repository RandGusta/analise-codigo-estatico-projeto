package servico;


import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import modelo.Usuario;
import modelo.enums.TipoUsuario;
import org.mindrot.jbcrypt.BCrypt;

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
            String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
            Usuario usuario = new Usuario(nome, email, senhaHash, tipoUsuario);
            usuarioDAO.adicionarUsuario(usuario);
        }


    }



    public Usuario validarLoginUsuario(String email, String senha){
        Usuario usuario = usuarioDAO.buscarUsuarioPorEmail(email);
        if(usuario == null) return null;

         if(BCrypt.checkpw(senha, usuario.getSenha())){ // descriptografa antes de comparar
             return usuario;
         }
         return null;
    }




    private TipoUsuario definirTipoUsuario(){
        if(usuarioDAO.contarUsuariosCadastrados() == 0L){
            return TipoUsuario.ADM;
        } else {
            return TipoUsuario.PADRAO;
        }
    }

}

package dao;

import modelo.Projeto;
import modelo.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void adicionarUsuario(Usuario usuario);
    void deletarUsuarioPorId(Long id);
    Usuario buscarUsuarioPorId(Long id);
    List<Usuario> listarUsuario();
    Usuario buscarUsuarioPorEmail(String email);
    Long contarUsuariosCadastrados();
}

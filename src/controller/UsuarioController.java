package controller;
import dao.impl.UsuarioDAOImpl;
import modelo.Usuario;
import modelo.enums.TipoUsuario;
import servico.ServicoUsuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("cadastrar-usuario\"")
public class UsuarioController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPC");
        EntityManager em = emf.createEntityManager();
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(em);
        ServicoUsuario servicoUsuario = new ServicoUsuario(usuarioDAO);

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        servicoUsuario.adicionarUsuario(nome, email, senha);

        response.getWriter().write("dados recebidos!!!");
    }




}

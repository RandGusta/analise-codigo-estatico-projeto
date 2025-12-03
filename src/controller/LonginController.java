package controller;

import com.sun.net.httpserver.HttpsServer;
import dao.impl.UsuarioDAOImpl;
import modelo.Usuario;
import servico.ServicoUsuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/efetuar-login")
public class LonginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPC");
        EntityManager em = emf.createEntityManager();
        try{
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(em);
        ServicoUsuario servicoUsuario = new ServicoUsuario(usuarioDAO);

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario loginValidado = servicoUsuario.validarLoginUsuario(email, senha);

        if(loginValidado != null){
            HttpSession session = request.getSession();

            session.setAttribute("usuarioLogado", loginValidado);
            response.sendRedirect("meus-projetos");

        } else {
            response.sendRedirect("index.html?erro=true");
        }
        } catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("erro: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }

}

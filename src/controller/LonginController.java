package controller;

import com.sun.net.httpserver.HttpsServer;
import dao.impl.UsuarioDAOImpl;
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


@WebServlet("efetuar-login")
public class LonginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPC");
        EntityManager em = emf.createEntityManager();
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(em);
        ServicoUsuario servicoUsuario = new ServicoUsuario(usuarioDAO);

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        boolean loginValidado = servicoUsuario.validarLoginUsuario(email, senha);

        if(loginValidado){
            HttpSession session = request.getSession();

            session.setAttribute("usuarioLogado", email);
            response.sendRedirect("index.html");

        } else {
            response.sendRedirect("login.html?erro=true");
        }

        em.close();
    }

}

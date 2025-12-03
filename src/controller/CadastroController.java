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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cadastrar-usuario")
public class CadastroController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPC");
        EntityManager em = emf.createEntityManager();
        try{
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(em);
        ServicoUsuario servicoUsuario = new ServicoUsuario(usuarioDAO);

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        try {
            servicoUsuario.adicionarUsuario(nome, email, senha);
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", email);
            response.sendRedirect("home.html");
        } catch (RuntimeException e) {
            e.printStackTrace();
            response.sendRedirect("index.html?erro=true");
        }

    } catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("erro: " + e.getMessage());
        } finally {
            // 3. FECHA TUDO! (A ordem importa: fecha o filho, depois o pai)
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close(); // <--- ESSA LINHA VAI SALVAR SEU BANCO DE TRAVAR
            }


        }
    }




}

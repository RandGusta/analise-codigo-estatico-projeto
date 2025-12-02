package controller;

import dao.impl.ArquivoCodigoDAOImpl;
import dao.impl.ProjetoDAOImpl;
import modelo.ArquivoCodigo;
import modelo.Projeto;
import org.hibernate.annotations.CreationTimestamp;
import servico.ServicoProjeto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/meus-projetos")
public class ListarProjetosController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPC");
        EntityManager em = emf.createEntityManager();
        try {

            ArquivoCodigoDAOImpl arquivoCodigo = new ArquivoCodigoDAOImpl(em);
            ProjetoDAOImpl projetoDAO = new ProjetoDAOImpl(em);


            ServicoProjeto servicoProjeto = new ServicoProjeto(projetoDAO, arquivoCodigo);

            List<Projeto> listaProjetos = servicoProjeto.listarProjeto();

            request.setAttribute("listaProjetos", listaProjetos);
            request.getRequestDispatcher("projetosGerais.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(em.isOpen()) em.close();
        }


    }


}

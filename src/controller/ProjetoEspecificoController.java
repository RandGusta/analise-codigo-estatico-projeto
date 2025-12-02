package controller;

import dao.impl.ArquivoCodigoDAOImpl;
import dao.impl.ProjetoDAOImpl;
import modelo.Projeto;
import servico.ServicoProjeto;

import javax.persistence.*;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/projeto-especifico")
public class ProjetoEspecificoController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPC");
        EntityManager em = emf.createEntityManager();

        try{
            ProjetoDAOImpl projetoDAO = new ProjetoDAOImpl(em);
            ArquivoCodigoDAOImpl arquivoCodigoDAO = new ArquivoCodigoDAOImpl(em);
            ServicoProjeto servicoProjeto = new ServicoProjeto(projetoDAO, arquivoCodigoDAO);

            String id = request.getParameter("id");
            Long idProjeto = Long.parseLong(id);

            Projeto projeto = servicoProjeto.buscarProjeto(idProjeto);

            if(projeto != null){ // evitar o LazyInitialization
                projeto.getArquivos().size();
            }


        }
    }

}

package controller;

import dao.impl.ArquivoCodigoDAOImpl;
import dao.impl.ProjetoDAOImpl;
import servico.ServicoProjeto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/upload-projeto")
@MultipartConfig // obrigatorio para receber arquivos
public class UpLoadProjeto extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPC");
        EntityManager em = emf.createEntityManager();

        ProjetoDAOImpl projetoDAO = new ProjetoDAOImpl(em);
        ArquivoCodigoDAOImpl arquivoCodigoDAO = new ArquivoCodigoDAOImpl(em);
        ServicoProjeto servicoProjeto = new ServicoProjeto(projetoDAO, arquivoCodigoDAO);

        request.getPart("file");





    }

}

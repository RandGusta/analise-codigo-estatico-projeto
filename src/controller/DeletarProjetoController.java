package controller;

import dao.impl.ProjetoDAOImpl;
import dao.impl.ArquivoCodigoDAOImpl;
import servico.ServicoProjeto;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deletar-projeto")
public class DeletarProjetoController extends HttpServlet{


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            ProjetoDAOImpl projetoDAO = new ProjetoDAOImpl();
            ArquivoCodigoDAOImpl arquivoCodigoDAO = new ArquivoCodigoDAOImpl();
            ServicoProjeto servicoProjeto = new ServicoProjeto(projetoDAO, arquivoCodigoDAO);

            String idString = request.getParameter("id");

            if(idString != null){
                Long id = Long.parseLong(idString);
                servicoProjeto.deletarProjeto(id);
            }

            response.sendRedirect("meus-projetos");

        } catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("erro ao deletar : " + e.getMessage());
        }

    }

}

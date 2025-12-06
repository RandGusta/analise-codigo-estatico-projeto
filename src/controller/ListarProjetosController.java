package controller;

import dao.impl.ArquivoCodigoDAOImpl;
import dao.impl.ProjetoDAOImpl;
import modelo.Projeto;
import modelo.Usuario;
import servico.ServicoProjeto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/meus-projetos")
public class ListarProjetosController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            HttpSession session = request.getSession();
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
            if(usuarioLogado == null){
                response.sendRedirect("index.html");
                return;
            }


            ArquivoCodigoDAOImpl arquivoCodigo = new ArquivoCodigoDAOImpl();
            ProjetoDAOImpl projetoDAO = new ProjetoDAOImpl();

            ServicoProjeto servicoProjeto = new ServicoProjeto(projetoDAO, arquivoCodigo);

            List<Projeto> listaProjetos = servicoProjeto.listarProjetoPorUsuario(usuarioLogado.getId());

            request.setAttribute("listaProjetos", listaProjetos);
            request.getRequestDispatcher("projetosGerais.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}

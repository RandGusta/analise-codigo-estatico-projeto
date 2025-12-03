package controller;

import dao.impl.ArquivoCodigoDAOImpl;
import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import servico.ServicoAnalise;

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

@WebServlet("/analisar-arquivos")
public class AnalisarCodigoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("meuPC");
            em = emf.createEntityManager();

            ArquivoCodigoDAOImpl arquivoDAO = new ArquivoCodigoDAOImpl(em);
            ServicoAnalise servicoAnalise = new ServicoAnalise(arquivoDAO);


            String[] idsSelecionados = request.getParameterValues("arquivosSelecionados");
            String idProjeto = request.getParameter("idProjeto");


            if (idsSelecionados == null || idsSelecionados.length == 0) {
                response.sendRedirect("projeto-especifico?id=" + idProjeto);
                return;
            }


            List<ArquivoCodigo> arquivosParaAnalisar = servicoAnalise.buscarArquivosPorIds(idsSelecionados);


            List<Ocorrencia> ocorrencias = servicoAnalise.analisarArquivos(arquivosParaAnalisar);


            request.setAttribute("listaOcorrencias", ocorrencias);
            request.setAttribute("idProjeto", idProjeto);


            request.getRequestDispatcher("resultadoAnalise.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<h3>Erro interno na análise: " + e.getMessage() + "</h3>");

            String idProjeto = request.getParameter("idProjeto");
            if (idProjeto != null) {
                response.getWriter().write("<a href='projeto-especifico?id=" + idProjeto + "'>Voltar ao Projeto</a>");
            }
        } finally {

            if (em != null && em.isOpen()) em.close();
            if (emf != null && emf.isOpen()) emf.close();
        }
    }
}
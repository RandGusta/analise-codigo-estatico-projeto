package controller;

import dao.impl.ArquivoCodigoDAOImpl;
import dao.impl.ProjetoDAOImpl;
import modelo.ArquivoCodigo;
import modelo.Projeto;
import modelo.Usuario;
import servico.ServicoArquivoCodigo;
import servico.ServicoProjeto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

@WebServlet("/upload-projeto")
@MultipartConfig // obrigatorio para receber arquivos
public class UpLoadProjeto extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPC");
        EntityManager em = emf.createEntityManager();

        try
        {
            HttpSession session = request.getSession(); // verificar se alguem esta logado
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

            // se não tiver logado
            if(usuario == null){
                System.out.println("Erro: Usuario não encontrado na sessão atual");
                response.sendRedirect("index.html");
                return;
            }

            ProjetoDAOImpl projetoDAO = new ProjetoDAOImpl(em);
            ArquivoCodigoDAOImpl arquivoCodigoDAO = new ArquivoCodigoDAOImpl(em);
            ServicoProjeto servicoProjeto = new ServicoProjeto(projetoDAO, arquivoCodigoDAO);
            ServicoArquivoCodigo servicoArquivoCodigo = new ServicoArquivoCodigo();

            String nomeProjeto = request.getParameter("nomeProjeto");


            List<ArquivoCodigo> listarArquivos = new ArrayList<>(); // para pegar os arquivos
            Collection<Part> partes = request.getParts();

            for (Part part : partes) {

                if (nomeProjeto.equals(part.getName())) {
                    try(Scanner scanner = new Scanner(part.getInputStream())){
                        if(scanner.hasNext()){
                            nomeProjeto = scanner.useDelimiter("\\A").next();
                        }
                    }
                    continue;
                }
                ArquivoCodigo arquivoCodigo = servicoArquivoCodigo.processarArquivoUploadProjeto(part);

                if (arquivoCodigo != null) {
                    listarArquivos.add(arquivoCodigo);
                }

            }




            if (listarArquivos.isEmpty()) {
                throw new RuntimeException("nenhum arquivo .js válido encontrado!!");
            }

            Projeto projeto = new Projeto(nomeProjeto, listarArquivos);
            projeto.setUsuario(usuario);
            servicoProjeto.adicionarProjeto(projeto);
            response.sendRedirect("meus-projetos");

    } catch (Exception e) {
            e.printStackTrace();
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

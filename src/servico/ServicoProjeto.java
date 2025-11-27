package servico;

import dao.impl.ArquivoCodigoDAOImpl;
import modelo.ArquivoCodigo;
import modelo.Projeto;
import dao.impl.ProjetoDAOImpl;

import java.util.List;

public class ServicoProjeto {      // orquestrador para salvar o arquivoCodigo junto
    private ArquivoCodigoDAOImpl arquivoCodigoDAO;
    private ProjetoDAOImpl projetoDAO;


    public ServicoProjeto(ProjetoDAOImpl projetoDAO, ArquivoCodigoDAOImpl arquivoCodigoDAO){
        this.projetoDAO = projetoDAO;
        this.arquivoCodigoDAO = arquivoCodigoDAO;
    }

    public void adicionarProjeto(Projeto projeto, List<ArquivoCodigo> listaArquivos){

        if(projeto.getNomeProjeto() == null || projeto.getNomeProjeto().isEmpty()){

            throw new RuntimeException("O projeto precisa de um nome!!");
        }

        projetoDAO.inserirProjeto(projeto);
        System.out.println("Projeto salvo com ID:" + projeto.getId());

        for(ArquivoCodigo arq : listaArquivos){
            arq.setProjeto(projeto);
            arquivoCodigoDAO.adicionarArquivo(arq);
        }



    }


}

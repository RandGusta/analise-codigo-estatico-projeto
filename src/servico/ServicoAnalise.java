package servico;

import dao.impl.ArquivoCodigoDAOImpl;
import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import regra.*;
import regra.javaScript.RegraComplexidadeCiclomaticaJS;
import regra.javaScript.RegraQuantidadeParametrosJS;
import regra.javaScript.RegraTamanhoNomeJS;
import regra.javaScript.RegraVerificacaoComentarioFuncaoJS;


import java.util.ArrayList;
import java.util.List;

public class ServicoAnalise {

    private ArquivoCodigoDAOImpl arquivoDAO;
    private List<RegraAnalise> regras = new ArrayList<>();

    public ServicoAnalise(ArquivoCodigoDAOImpl arquivoDAO) {
        this.arquivoDAO = arquivoDAO;

        this.regras.add(new RegraTamanhoNomeJS());
        this.regras.add(new RegraVerificacaoComentarioFuncaoJS());
        this.regras.add(new RegraVerificacaoComentarioFuncaoJS());
        this.regras.add(new RegraComplexidadeCiclomaticaJS());

    }


    public List<ArquivoCodigo> buscarArquivosPorIds(String[] idsSelecionados) {
        List<ArquivoCodigo> listaArquivos = new ArrayList<>();
        if (idsSelecionados != null) {
            for (String idStr : idsSelecionados) {
                try {
                    Long id = Long.parseLong(idStr);
                    ArquivoCodigo arq = arquivoDAO.buscarArquivoPorId(id);
                    if (arq != null) listaArquivos.add(arq);
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido ignorado: " + idStr);
                }
            }
        }
        return listaArquivos;
    }

    public List<Ocorrencia> analisarArquivos(List<ArquivoCodigo> arquivos) {
        List<Ocorrencia> relatorioGeral = new ArrayList<>();

        for (ArquivoCodigo arquivo : arquivos) {
            for (RegraAnalise regra : regras) {
                // A regra agora usa AnaliseUtils internamente
                List<Ocorrencia> problemas = regra.aplicar(arquivo);
                relatorioGeral.addAll(problemas);
            }
        }
        return relatorioGeral;
    }
}
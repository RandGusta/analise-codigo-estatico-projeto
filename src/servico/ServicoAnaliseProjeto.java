package servico;

import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import regra.RegraAnalise;
import regra.javaScript.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ServicoAnaliseProjeto {

    private List<RegraAnalise> regras;

    public ServicoAnaliseProjeto() {
        regras = List.of(
                new RegraTamanhoNomeJS(),
                new RegraQuantidadeParametrosJS(),
                new RegraVerificacaoComentarioFuncaoJS(),
                new RegraComplexidadeCiclomaticaJS()
        );
    }

    public List<Ocorrencia> analisarArquivo(ArquivoCodigo arquivo) {
        List<Ocorrencia> todasOcorrencias = new ArrayList<>();

        for (RegraAnalise regra : regras) {
            todasOcorrencias.addAll(regra.aplicar(arquivo));
        }

        return todasOcorrencias;
    }

    public Map<String, List<Ocorrencia>> analisarArquivos(List<ArquivoCodigo> arquivos) {
        Map<String, List<Ocorrencia>> resultados = new HashMap<>();

        for (ArquivoCodigo arquivo : arquivos) {
            resultados.put(arquivo.getNome(), analisarArquivo(arquivo));
        }

        return resultados;
    }
}

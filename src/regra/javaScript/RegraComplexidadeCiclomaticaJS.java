package regra.javaScript;

import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import modelo.enums.TipoProblema;
import modelo.enums.estruturasDecisao.EstruturaDecisaoJs;
import modelo.enums.tipoDeclaracao.TipoDeclaracaoJS;
import regra.RegraAnalise;
import util.AnaliseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RegraComplexidadeCiclomaticaJS extends RegraAnaliseJS implements RegraAnalise {


    @Override
    public List<Ocorrencia> aplicar(ArquivoCodigo arquivoCodigo) {
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String conteudo = arquivoCodigo.getConteudo();

        if (conteudo == null) return ocorrencias;

        for (TipoDeclaracaoJS tipo : TipoDeclaracaoJS.values()) {

            Matcher matcherFuncao = AnaliseUtil.obterFuncoes(conteudo, tipo);

            while (matcherFuncao.find()) {

                int linha = AnaliseUtil.calcularLinha(conteudo, matcherFuncao.start());

                String nomeFuncao = matcherFuncao.group(1);
                String corpoFuncao = matcherFuncao.group();

                int complexidade = calcularComplexidade(corpoFuncao);

                TipoProblema tipoProblema = null;
                if (complexidade <= 10) {
                    continue;
                } else if (complexidade <= 20) {
                    tipoProblema = TipoProblema.COMPLEXIDADE_CICLOMATICA_MODERADA;
                } else if (complexidade <= 50) {
                    tipoProblema = TipoProblema.COMPLEXIDADE_CICLOMATICA_ALTA;
                } else {
                    tipoProblema = TipoProblema.COMPLEXIDADE_CICLOMATICA_CRITICA;
                }

                Ocorrencia ocorrencia = new Ocorrencia(
                        tipoProblema.getCodigo(),
                        arquivoCodigo,
                        linha,
                        tipoProblema.getDescricao() + " = " + complexidade,
                        nomeFuncao
                );
                ocorrencias.add(ocorrencia);
            }
        }

        return ocorrencias;
    }

    private int calcularComplexidade(String codigoFuncao) {
        int complexidade = 1;
        for (EstruturaDecisaoJs ed : EstruturaDecisaoJs.values()) {

            Matcher matcher = AnaliseUtil.obterEstruturaDecisao(codigoFuncao, ed);

            while (matcher.find()) {
                if (matcher.group(1) != null || matcher.group(2) != null) {
                    complexidade++;
                }
            }
        }

        return complexidade;
    }
}
package regra.javaScript;

import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import modelo.enums.TipoProblema;
import modelo.enums.tipoDeclaracao.TipoDeclaracaoJS;
import modelo.enums.estruturasDecisao.EstruturaDecisaoJs;
import regra.RegraAnalise;
import servico.ServicoAnalise;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RegraComplexidadeCiclomaticaJS extends RegraAnaliseJS implements RegraAnalise {

    private ServicoAnalise servicoAnalise = new ServicoAnalise();

    @Override
    public List<Ocorrencia> aplicar(ArquivoCodigo arquivoCodigo) {
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String conteudo = arquivoCodigo.getConteudo();

        // Itera sobre todos os tipos de declaração de função
        for (TipoDeclaracaoJS tipo : TipoDeclaracaoJS.values()) {
            Matcher matcherFuncao = servicoAnalise.obterFuncoes(conteudo, tipo);

            while (matcherFuncao.find()) {
                int linha = conteudo.substring(0, matcherFuncao.start()).split("\n").length;
                String nomeFuncao = matcherFuncao.group(1);
                String corpoFuncao = matcherFuncao.group(); // conteúdo da função

                int complexidade = calcularComplexidade(corpoFuncao);

                TipoProblema tipoProblema = null;
                if (complexidade <= 10) {
                    // simples, não gera ocorrência
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

    // Método privado para calcular complexidade ciclomática
    private int calcularComplexidade(String codigoFuncao) {
        int complexidade = 1; // sempre começa com 1

        // Itera sobre todos os padrões de decisão
        for (EstruturaDecisaoJs ed : EstruturaDecisaoJs.values()) {
            Matcher matcher = servicoAnalise.obterEstruturaDecisao(codigoFuncao, ed);
            while (matcher.find()) {
                if (matcher.group(1) != null || matcher.group(2) != null) {
                    complexidade++;
                }
            }
        }

        return complexidade;
    }
}

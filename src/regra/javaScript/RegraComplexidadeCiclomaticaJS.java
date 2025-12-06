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

                String corpoFuncao = extrairCorpoFuncao(conteudo, matcherFuncao.end());

                if (corpoFuncao.isEmpty()) {
                    continue; // Se não achou corpo --> pula
                }

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
                        tipoProblema.getDescricao() + " (Nível: " + complexidade + ")",
                        nomeFuncao
                );
                ocorrencias.add(ocorrencia);
            }
        }
        return ocorrencias;
    }

    // procurando o bloco '{ }'
    private String extrairCorpoFuncao(String codigo, int inicioBusca) {
        int indiceAbreChave = codigo.indexOf("{", inicioBusca);
        if (indiceAbreChave == -1) return "";

        int contadorChaves = 0;
        int indiceFechaChave = -1;

        // contando '{' '}'
        for (int i = indiceAbreChave; i < codigo.length(); i++) {
            char c = codigo.charAt(i);
            if (c == '{') {
                contadorChaves++;
            } else if (c == '}') {
                contadorChaves--;
                if (contadorChaves == 0) {
                    indiceFechaChave = i;
                    break;
                }
            }
        }

        if (indiceFechaChave != -1) {
            return codigo.substring(indiceAbreChave, indiceFechaChave + 1);
        }
        return "";
    }

    private int calcularComplexidade(String codigoFuncao) {
        int complexidade = 1;
        for (EstruturaDecisaoJs ed : EstruturaDecisaoJs.values()) {
            Matcher matcher = AnaliseUtil.obterEstruturaDecisao(codigoFuncao, ed);
            while (matcher.find()) {
                // Contando --> if, while, for, case, etc
                complexidade++;
            }
        }
        return complexidade;
    }
}
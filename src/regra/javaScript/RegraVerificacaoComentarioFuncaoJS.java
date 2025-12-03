package regra.javaScript;

import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import modelo.enums.TipoProblema;
import modelo.enums.comentariosCodigo.ComentarioJS;
import modelo.enums.tipoDeclaracao.TipoDeclaracaoJS;
import regra.RegraAnalise;
import util.AnaliseUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RegraVerificacaoComentarioFuncaoJS extends RegraAnaliseJS implements RegraAnalise {

    // REMOVIDO: ServicoAnalise servicoAnalise = new ServicoAnalise();
    // Isso quebrava o código causando dependência circular.

    @Override
    public List<Ocorrencia> aplicar(ArquivoCodigo arquivoCodigo) {
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String conteudo = arquivoCodigo.getConteudo();

        if (conteudo == null) return ocorrencias;

        for (TipoDeclaracaoJS tipo : TipoDeclaracaoJS.values()) {


            Matcher matcherFuncao = AnaliseUtil.obterFuncoes(conteudo, tipo);

            // TODO: verificar depois function dentro de function
            while (matcherFuncao.find()) {
                int inicioFuncao = matcherFuncao.start();
                int fimFuncao = matcherFuncao.end();


                int linha = AnaliseUtil.calcularLinha(conteudo, matcherFuncao.start());

                String trechoFuncao = conteudo.substring(inicioFuncao, fimFuncao);
                int indiceAnteriorFuncao = conteudo.lastIndexOf("\n", inicioFuncao - 1);

                if (indiceAnteriorFuncao != -1) {
                    int indiceLinhaAnteriorFuncao = conteudo.lastIndexOf("\n", indiceAnteriorFuncao - 1);
                    if (indiceLinhaAnteriorFuncao == -1) {
                        indiceLinhaAnteriorFuncao = 0;
                    }
                    String linhaAnterior = conteudo.substring(indiceLinhaAnteriorFuncao, indiceAnteriorFuncao);
                    boolean temComentario = false;

                    for (ComentarioJS comentarioJS : ComentarioJS.values()) {

                        Matcher matcherComentarioAntes = AnaliseUtil.obterComentario(linhaAnterior, comentarioJS);
                        Matcher matcherComentarioEntre = AnaliseUtil.obterComentario(trechoFuncao, comentarioJS);

                        if (matcherComentarioAntes.find() || matcherComentarioEntre.find()) {
                            temComentario = true;
                            break;
                        }
                    }

                    if (!temComentario) {
                        TipoProblema tipoProblema = TipoProblema.FUNCAO_SEM_COMENTARIO;
                        Ocorrencia ocorrencia = new Ocorrencia(
                                tipoProblema.getCodigo(),
                                arquivoCodigo,
                                linha,
                                tipoProblema.getDescricao(),
                                matcherFuncao.group(1)
                        );
                        ocorrencias.add(ocorrencia);
                    }
                }
            }
        }

        return ocorrencias;
    }
}
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

    @Override
    public List<Ocorrencia> aplicar(ArquivoCodigo arquivoCodigo) {
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String conteudo = arquivoCodigo.getConteudo();

        if (conteudo == null) return ocorrencias;

        for (TipoDeclaracaoJS tipo : TipoDeclaracaoJS.values()) {
            Matcher matcherFuncao = AnaliseUtil.obterFuncoes(conteudo, tipo);

            while (matcherFuncao.find()) {
                int inicioFuncao = matcherFuncao.start();
                int fimFuncao = matcherFuncao.end();
                int linha = AnaliseUtil.calcularLinha(conteudo, inicioFuncao);
                String trechoFuncao = conteudo.substring(inicioFuncao, fimFuncao);
                boolean temComentario = false;

                // verifica comentario DENTRO da função
                for (ComentarioJS comentarioJS : ComentarioJS.values()) {
                    if (AnaliseUtil.obterComentario(trechoFuncao, comentarioJS).find()) {
                        temComentario = true;
                        break;
                    }
                }


                if (!temComentario) {
                    // Pega o trecho anterior ao início da função
                    int inicioBuscaAnterior = Math.max(0, inicioFuncao - 200);
                    String trechoAnterior = conteudo.substring(inicioBuscaAnterior, inicioFuncao);

                    for (ComentarioJS comentarioJS : ComentarioJS.values()) {
                        Matcher m = AnaliseUtil.obterComentario(trechoAnterior, comentarioJS);
                        // Verifica se achou um comentario no final do trecho
                        if (m.find()) {
                            temComentario = true;
                            break;
                        }
                    }
                }

                // se n achou
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
        return ocorrencias;
    }
}
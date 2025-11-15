package regra.javaScript;

import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import modelo.enums.TipoProblema;
import modelo.enums.comentariosCodigo.ComentarioJS;
import modelo.enums.tipoDeclaracao.TipoDeclaracaoJS;
import regra.RegraAnalise;
import servico.ServicoAnalise;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RegraVerificacaoComentarioFuncaoJS implements RegraAnalise {
    ServicoAnalise servicoAnalise = new ServicoAnalise();

    @Override
    public List<Ocorrencia> aplicar(ArquivoCodigo arquivoCodigo) {
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String conteudo = arquivoCodigo.getConteudo();

        for (TipoDeclaracaoJS tipo : TipoDeclaracaoJS.values()) {
            Matcher matcherFuncao = servicoAnalise.obterFuncoes(conteudo, tipo);

            // TODO: verificar depois function dentro de function
            while (matcherFuncao.find()) {
                int inicioFuncao = matcherFuncao.start();
                int fimFuncao = matcherFuncao.end();
                int linha = conteudo.substring(0, matcherFuncao.start()).split("\n").length;

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
                        Matcher matcherComentarioAntes = servicoAnalise.obterComentario(linhaAnterior, comentarioJS);
                        Matcher matcherComentarioEntre = servicoAnalise.obterComentario(trechoFuncao, comentarioJS);

                        if (matcherComentarioAntes.find() || matcherComentarioEntre.find()) {
                            temComentario = true;
                            break;
                        }
                    }

                    if (!temComentario) {
                        TipoProblema tipoProblema = TipoProblema.FUNCAO_SEM_COMENTARIO;
                        Ocorrencia ocorrencia = new Ocorrencia(tipoProblema.getCodigo(), arquivoCodigo,linha, tipoProblema.getDescricao(), matcherFuncao.group(1)
                        );
                        ocorrencias.add(ocorrencia);
                    }
                }
            }
        }

        return ocorrencias;
    }
}
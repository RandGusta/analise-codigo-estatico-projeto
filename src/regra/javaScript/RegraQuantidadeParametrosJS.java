package regra.javaScript;

import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import modelo.enums.TipoProblema;
import modelo.enums.tipoDeclaracao.TipoDeclaracaoJS;
import regra.RegraAnalise;
import util.AnaliseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RegraQuantidadeParametrosJS extends RegraAnaliseJS implements RegraAnalise {


    @Override
    public List<Ocorrencia> aplicar(ArquivoCodigo arquivoCodigo) {
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String conteudo = arquivoCodigo.getConteudo();

        if (conteudo == null) return ocorrencias;

        for (TipoDeclaracaoJS tipo : TipoDeclaracaoJS.values()) {


            Matcher matcher = AnaliseUtil.obterFuncoes(conteudo, tipo);

            while (matcher.find()) {
                if (matcher.groupCount() >= 2) {
                    int quantidadeParametros = AnaliseUtil.contarParametros(matcher.group(2));


                    int linha = AnaliseUtil.calcularLinha(conteudo, matcher.start());

                    if (quantidadeParametros > 5) {
                        TipoProblema tipoProblema = TipoProblema.QTD_PARAMETROS_EXECIDA;
                        Ocorrencia ocorrencia = new Ocorrencia(
                                tipoProblema.getCodigo(),
                                arquivoCodigo,
                                linha,
                                tipoProblema.getDescricao(),
                                matcher.group(1)
                        );
                        ocorrencias.add(ocorrencia);
                    }
                }
            }
        }
        return ocorrencias;
    }
}
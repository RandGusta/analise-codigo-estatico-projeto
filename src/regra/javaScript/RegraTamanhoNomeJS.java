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

public class RegraTamanhoNomeJS implements RegraAnalise {

    @Override
    public List<Ocorrencia> aplicar(ArquivoCodigo arquivo) {
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String codigo = arquivo.getConteudo();

        if (codigo == null) return ocorrencias;

        for (TipoDeclaracaoJS tipo : TipoDeclaracaoJS.values()) {

            // CORREÇÃO: Chamamos direto do Utilitário (sem precisar do Serviço)
            Matcher matcher = AnaliseUtil.obterFuncoes(codigo, tipo);

            while (matcher.find()) {

                if (matcher.groupCount() >= 1) {
                    String nome = matcher.group(1);


                    int linha = AnaliseUtil.calcularLinha(codigo, matcher.start());

                    if (nome.length() > 10) {
                        TipoProblema problema = TipoProblema.NOME_FUNCAO_LONGO;


                        Ocorrencia ocorrencia = new Ocorrencia(
                                problema.getCodigo(),
                                arquivo,
                                linha,
                                problema.getDescricao(),
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
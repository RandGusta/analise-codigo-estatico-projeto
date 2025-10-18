package regra.javaScript;

import modelo.ArquivoCodigo;
import modelo.TipoDeclaracao;
import modelo.TipoProblema;
import modelo.tipoDeclaracao.TipoDeclaracaoJS;
import regra.RegraAnalise;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Ocorrencia;
import servico.ServicoAnalise;

public class RegraTamanhoNomeJS implements RegraAnalise {
        private ServicoAnalise servicoAnalise = new ServicoAnalise();

    @Override
    public List<Ocorrencia> aplicar(ArquivoCodigo arquivo){
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String codigo = arquivo.getConteudo();

        for(TipoDeclaracaoJS tipo: TipoDeclaracaoJS.values()){
            Matcher matcher = servicoAnalise.obterFuncoes(codigo, tipo);

            while(matcher.find()){
                if(matcher.groupCount() >= 1){
                    String nome = servicoAnalise.extrairNome(matcher, tipo);
                    int linha = codigo.substring(0, matcher.start()).split("\n").length;
                    if(nome.length() > 50){
                        TipoProblema problema = TipoProblema.QTD_PARAMETROS_EXECIDA;
                        Ocorrencia ocorrencia = new Ocorrencia(problema.getCodigo(), arquivo.getNome(), linha, problema.getDescricao(), matcher.group(1));
                        ocorrencias.add(ocorrencia);
                    }
                }
            }

        }

        return ocorrencias;
    }
}

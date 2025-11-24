package regra.javaScript;

import modelo.ArquivoCodigo;
import modelo.enums.TipoProblema;
import modelo.enums.tipoDeclaracao.TipoDeclaracaoJS;
import regra.RegraAnalise;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import modelo.Ocorrencia;
import servico.ServicoAnalise;

public class RegraTamanhoNomeJS  extends RegraAnaliseJS implements RegraAnalise {
        private ServicoAnalise servicoAnalise = new ServicoAnalise();

    @Override
    public List<Ocorrencia> aplicar(ArquivoCodigo arquivo){
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String codigo = arquivo.getConteudo();

        for(TipoDeclaracaoJS tipo: TipoDeclaracaoJS.values()){
            Matcher matcher = servicoAnalise.obterFuncoes(codigo, tipo);

            while(matcher.find()){

                if(matcher.groupCount() >= 1){
                    String nome = matcher.group(1);
                    int linha = codigo.substring(0, matcher.start()).split("\n").length;
                    if(nome.length() > 10){
                        TipoProblema problema = TipoProblema.NOME_FUNCAO_LONGO;
                        Ocorrencia ocorrencia = new Ocorrencia(problema.getCodigo(), arquivo, linha, problema.getDescricao(), matcher.group(1));
                        ocorrencias.add(ocorrencia);
                    }
                }
            }

        }

        return ocorrencias;
    }
}

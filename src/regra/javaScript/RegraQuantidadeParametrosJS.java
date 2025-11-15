package regra.javaScript;

import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import modelo.enums.TipoProblema;
import modelo.enums.tipoDeclaracao.TipoDeclaracaoJS;
import regra.RegraAnalise;
import servico.ServicoAnalise;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RegraQuantidadeParametrosJS implements RegraAnalise {
    private ServicoAnalise servicoAnalise = new ServicoAnalise();



    @Override
    public List<Ocorrencia> aplicar(ArquivoCodigo arquivoCodigo){
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String conteudo = arquivoCodigo.getConteudo();

        for(TipoDeclaracaoJS tipo : TipoDeclaracaoJS.values()){
            Matcher matcher = servicoAnalise.obterFuncoes(conteudo, tipo);

            while(matcher.find()){
                if(matcher.groupCount() >= 2){
                    int quantidadeParamatros = servicoAnalise.contarParametros(matcher.group(2));
                    int linha = conteudo.substring(0, matcher.start()).split("\n").length;
                    if(quantidadeParamatros > 5){
                        TipoProblema tipoProblema = TipoProblema.QTD_PARAMETROS_EXECIDA;
                        Ocorrencia ocorrencia = new Ocorrencia(tipoProblema.getCodigo(), arquivoCodigo, linha, tipoProblema.getDescricao(), matcher.group(1));
                        ocorrencias.add(ocorrencia);
                    }


                }
            }
        }
        return ocorrencias;
    }

}

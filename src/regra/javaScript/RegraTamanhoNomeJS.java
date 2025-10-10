package regra.javaScript;

import modelo.ArquivoCodigo;
import modelo.TipoDeclaracao;
import modelo.tipoDeclaracao.TipoDeclaracaoJS;
import regra.RegraAnalise;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Ocorrencia;
import servico.ServicoAnalise;

public class RegraTamanhoNomeJS implements RegraAnalise {

    @Override
    public List<Ocorrencia> aplicar(ArquivoCodigo arquivo){
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String codigo = arquivo.getConteudo();
        for(TipoDeclaracaoJS tipo: TipoDeclaracaoJS.values()){
            Pattern pattern = tipo.getPadrao();
            Matcher matcher = pattern.matcher(codigo);

            while(matcher.find()){
                if(matcher.groupCount() >= 1){
                    String nome = matcher.group(1);
                    int linha = codigo.substring(0, matcher.start()).split("\n").length;
                    if(nome.length() > 50){
                        MenssagemErro =
                        Ocorrencia ocorrencia = new Ocorrencia("Nome muito extenso", arquivo.getNome(), linha,  );
                    }
                }
            }

        }
    }
}

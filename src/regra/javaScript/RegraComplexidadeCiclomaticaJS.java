package regra.javaScript;

import modelo.ArquivoCodigo;

import modelo.enums.estruturasDecisao.EstruturaDecisaoJs;

import java.util.regex.Matcher;
import servico.ServicoAnalise;

public class RegraComplexidadeCiclomaticaJS{
    ServicoAnalise servicoAnalise = new ServicoAnalise();
    EstruturaDecisaoJs estruturaDecisaoJs = EstruturaDecisaoJs.values()[0];


    public int calcularComplexidade(ArquivoCodigo arquivoCodigo){
        int complexidade = 0;
        String conteudo = arquivoCodigo.getConteudo();

        Matcher matcher = servicoAnalise.obterEstruturaDecisao(conteudo, estruturaDecisaoJs);
        while(matcher.find()){
            if(matcher.group(1) != null) complexidade ++;
            if(matcher.group(2) != null) complexidade ++;
        }

        return complexidade;


    }



}

package servico;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ServicoAnalise {

    function\s+([a-zA-Z0-9_]+)\s*\((.*\))    public void verificarQuantidadeParametros(String arquivoString){
        //Pattern padrao = Pattern.compile("sua_regex_aqui");
        //Matcher matcher = padrao.matcher(texto);
        Pattern padraoFunca = Pattern.compile("");
        Matcher matcher = padraoFunca.matcher(arquivoString);
        while(matcher.find()){// andando pela string
            int cont = 0;
            String parametros = matcher.group(2);
            String[] vetorParametros = parametros.split(",");
            for(int i = 0; i< vetorParametros.length; i++){
                cont++;
            }
            System.out.println("A função " + matcher.group(0) + " tem " + cont + " parâmetros"); // depois substituir por group(1)

        }
    }





}

package servico;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ServicoAnalise {

    public void contarFuncoes(String arquivoString){
        //Pattern padrao = Pattern.compile("sua_regex_aqui");
        //Matcher matcher = padrao.matcher(texto);
        Pattern padraoFunca = Pattern.compile("function\\s+[a-zA-Z0-9_]+\\s*\\(.*\\)");
        Matcher matcher = padraoFunca.matcher(arquivoString);
        while(matcher.find()){// andando pela string
            System.out.println("Função encontrada: "  + matcher.group());
        }
    }


}

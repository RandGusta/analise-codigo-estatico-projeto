package servico;

import modelo.TipoDeclaracao;
import modelo.TipoDeclaracaoJS;

import javax.swing.plaf.multi.MultiPopupMenuUI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ServicoAnalise {

    public void verificarQuantidadeParametros(String arquivoString) {
        // for para percorrer os enums
        for (TipoDeclaracaoJS tipo : TipoDeclaracaoJS.values()) {
            Pattern padrao = tipo.getPadrao();
            Matcher matcher = padrao.matcher(arquivoString);
            while (matcher.find()) {

                if (matcher.groupCount() >= 2) {
                    String nome = matcher.group(1);
                    String parametros = matcher.group(2);
                    String[] vetorParamtros = parametros.split(",");
                    int cont = 0;
                    for (String p : vetorParamtros) {
                        if (!p.trim().isEmpty()) cont++;
                    }

                    if(cont > 10){ // Vai mudar --> pegar base de uma fonte
                        System.out.println("A função " + matcher.group() + " tem " + cont + " parâmetros \n" + (cont  - 10) + " a mais que o recomendado");
                    }


                }
            }
        }
    }


    public void verificarTamanhoNomeFuncao(String arquivoString){
        for (TipoDeclaracaoJS tipo : TipoDeclaracaoJS.values()) {
            Pattern padrao = tipo.getPadrao();
            Matcher matcher = padrao.matcher(arquivoString);
            while (matcher.find()) {

                if (matcher.groupCount() >= 1) {
                    String nome = matcher.group(1);
                    int cont = nome.length();
                if (cont > 50) {  // Vai mudar --> pegar base de uma fonte
                    System.out.println("a função " + nome + " possui " + cont + " caractéres em seu nome oque ultrapassa o recomendado");
                }
            }

        }



    }





}
}

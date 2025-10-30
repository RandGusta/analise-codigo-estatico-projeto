package app;
import enums.TipoLinguagem;
import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import regra.javaScript.RegraComplexidadeCiclomaticaJS;
import regra.javaScript.RegraQuantidadeParametrosJS;
import regra.javaScript.RegraTamanhoNomeJS;
import regra.javaScript.RegraVerificacaoComentarioFuncaoJS;
import servico.ServicoAnalise;
import util.FuncoesUteis;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        ServicoAnalise servicoAnalise = new ServicoAnalise();

        if(FuncoesUteis.verificarCaminho("C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\arquivoteste.txt")){
        String arquivo = FuncoesUteis.lerArquivoComoString("C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\arquivoteste.txt");
            RegraTamanhoNomeJS regraTamanhoNomeJS = new RegraTamanhoNomeJS();
            RegraQuantidadeParametrosJS regraQuantidadeParametros = new RegraQuantidadeParametrosJS();
            ArquivoCodigo arquivoCodigo = new ArquivoCodigo("arquivoteste", TipoLinguagem.JAVASCRIPT, arquivo);
            List<Ocorrencia> lista = regraTamanhoNomeJS.aplicar(arquivoCodigo);
            List<Ocorrencia> lista2 = regraQuantidadeParametros.aplicar(arquivoCodigo);

            for(Ocorrencia item: lista){
                System.out.println(item.ocorrenciaCompleta());
            }
            System.out.println();
            for(Ocorrencia item2: lista2){
                System.out.println(item2.ocorrenciaCompleta());
            }

            RegraComplexidadeCiclomaticaJS regraComplexidadeCiclomaticaJS = new RegraComplexidadeCiclomaticaJS();
            int complexidade = regraComplexidadeCiclomaticaJS.calcularComplexidade(arquivoCodigo);
            System.out.println( "complexidade ciclomática " + complexidade);


            RegraVerificacaoComentarioFuncaoJS regraVerificacaoComentarioFuncaoJS = new RegraVerificacaoComentarioFuncaoJS();
            List<Ocorrencia> lista3 = regraVerificacaoComentarioFuncaoJS.aplicar(arquivoCodigo);
            System.out.println();
            for(Ocorrencia  item3: lista3){
                System.out.println(item3.ocorrenciaCompleta());
            }

           


    }
    }
}

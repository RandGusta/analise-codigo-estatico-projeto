package app;
import enums.TipoLinguagem;
import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import regra.javaScript.RegraQuantidadeParametrosJS;
import regra.javaScript.RegraTamanhoNomeJS;
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
            for(Ocorrencia item: lista2){
                System.out.println(item.ocorrenciaCompleta());
            }


    }
    }
}

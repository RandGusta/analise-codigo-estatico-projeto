package app;
import servico.ServicoAnalise;
import util.FuncoesUteis;
public class Main {
    public static void main(String[] args) {
        ServicoAnalise servicoAnalise = new ServicoAnalise();

        if(FuncoesUteis.verificarCaminho("C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\arquivoteste.txt")){
        String arquivo = FuncoesUteis.lerArquivoComoString("C:\\Users\\gusta\\OneDrive\\Área de Trabalho\\arquivoteste.txt");
        servicoAnalise.verificarQuantidadeParametros(arquivo);
    }
    }
}

package app;
import modelo.enums.TipoLinguagem;
import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import regra.javaScript.RegraComplexidadeCiclomaticaJS;
import regra.javaScript.RegraQuantidadeParametrosJS;
import regra.javaScript.RegraTamanhoNomeJS;
import regra.javaScript.RegraVerificacaoComentarioFuncaoJS;
import servico.ServicoAnalise;
import util.FuncoesUteis;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPC");
            EntityManager em = emf.createEntityManager();


            System.out.println("Conexão realizada");

            em.close();
            emf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

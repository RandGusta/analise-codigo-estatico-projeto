package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelo.ArquivoCodigo;
import modelo.Ocorrencia;
import modelo.enums.TipoLinguagem;
import servico.ServicoAnaliseProjeto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/analisar-arquivos")
public class AnaliseController extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // lê JSON do body: { "arquivos": ["a.js","b.js"] }
        String json = request.getReader().lines().collect(Collectors.joining());
        Type tipo = new TypeToken<Map<String, List<String>>>(){}.getType();
        Map<String, List<String>> dados = gson.fromJson(json, tipo);
        List<String> arquivosSelecionados = dados.get("arquivos");

        if (arquivosSelecionados == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nenhum arquivo informado");
            return;
        }

        // Monta a lista de ArquivoCodigo a partir dos nomes
        List<ArquivoCodigo> arquivosParaAnalisar = new ArrayList<>();

        // *** ATENÇÃO: Aqui eu assumo que os arquivos já foram enviados anteriormente
        // e salvos em disco na pasta "uploads/" dentro do webapp.
        // Ajuste o caminho caso você salve em outro local.
        Path uploadsDir = Paths.get(getServletContext().getRealPath("/uploads"));

        for (String nomeArquivo : arquivosSelecionados) {
            Path arquivoPath = uploadsDir.resolve(nomeArquivo);
            String conteudo = "";
            String caminhoRelativo = arquivoPath.toString();

            if (Files.exists(arquivoPath)) {
                conteudo = Files.readString(arquivoPath, StandardCharsets.UTF_8);
            } else {
                // Se não encontrar, deixa conteudo vazio (ou registre/logue)
                // você pode também pular o arquivo em vez de adicioná-lo
            }


            // construtor: ArquivoCodigo(String nome, TipoLinguagem tipoLinguagem, String conteudo, String caminho)
            ArquivoCodigo ac = new ArquivoCodigo(
                    nomeArquivo,
                    TipoLinguagem.JAVASCRIPT, // ajuste o valor do enum se o seu for diferente
                    conteudo,
                    caminhoRelativo
            );
            arquivosParaAnalisar.add(ac);
        }

        // chama o serviço orquestrador que aplica todas as regras
        ServicoAnaliseProjeto servicoProjeto = new ServicoAnaliseProjeto();
        Map<String, List<Ocorrencia>> resultados = servicoProjeto.analisarArquivos(arquivosParaAnalisar);

        // salva na sessão para o /resultado-analise (ocorrencias.html irá buscar)
        HttpSession sessao = request.getSession();
        sessao.setAttribute("resultadoAnalise", resultados);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}

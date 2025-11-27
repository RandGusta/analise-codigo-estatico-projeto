package servico;

import modelo.ArquivoCodigo;
import modelo.enums.TipoLinguagem;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ServicoArquivoCodigo {

    public ArquivoCodigo processarArquivoUploadProjeto(Part part){
        try
        {
            String nomeArquivo = part.getSubmittedFileName();

            if(nomeArquivo == null && !nomeArquivo.endsWith(".js")){
                return null;
            }

            String conteudo = lerConteudoArquivo(part.getInputStream()); // getInputStream --> retorna binarios

            ArquivoCodigo arquivoCodigo = new ArquivoCodigo(nomeArquivo, TipoLinguagem.JAVASCRIPT, conteudo);

            return arquivoCodigo;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo" + part.getSubmittedFileName() );
        }
    }


    private String lerConteudoArquivo(InputStream inputStream) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){ // bufferReader --> Decorador
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        }

        }
    }


package dao;

import modelo.ArquivoCodigo;

import java.util.List;

public interface ArquivoCodigoDAO {
    void adicionarArquivo(ArquivoCodigo arquivoCodigo);
    void removerArquivoPorId(Long id);
    List<ArquivoCodigo> buscarArquivoPorNome(String nome);
    ArquivoCodigo buscarArquivoPorId(Long id);
    List<ArquivoCodigo> listarArquivos(Long idProjeto);
}

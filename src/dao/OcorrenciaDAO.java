package dao;

import modelo.Ocorrencia;
import java.util.List;

public interface OcorrenciaDAO {
    void inserir(Ocorrencia ocorrencia);
    Ocorrencia acharPorID(int id);
    void atualizar(Ocorrencia ocorrencia);
    void deletar(int id);
    List<Ocorrencia> listar();
}

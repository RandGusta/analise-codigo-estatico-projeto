package dao;

import modelo.Ocorrencia;

public interface OcorrenciaDAO {
    void inserir(Ocorrencia ocorrencia);
    Ocorrencia acharPorID(int id);
    void atualizar(Ocorrencia ocorrencia);
    void deletar(int id);
}

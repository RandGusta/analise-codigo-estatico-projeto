package dao;

import modelo.Ocorrencia;
import java.util.List;

public interface OcorrenciaDAO {
    void inserir(Ocorrencia ocorrencia);
    Ocorrencia acharPorID(Long ID);
    void deletar(Long ID);
    List<Ocorrencia> listar();
}

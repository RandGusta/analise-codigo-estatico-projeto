package dao;
import java.util.List;

import modelo.Projeto;

public interface ProjetoDAO {
    void inserirProjeto(Projeto projeto);
    Projeto buscarProjetoPorId(Long id);
    List<Projeto> listarProjetos();
    void deletarProjetoPorId(Long id);
    List<Projeto> buscarProjetoPorNome(String nome);
}

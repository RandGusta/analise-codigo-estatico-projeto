package dao.impl;

import dao.OcorrenciaDAO;
import modelo.Ocorrencia;

import java.sql.Connection;

public class OcorrenciaDAOImpl implements OcorrenciaDAO {
    private Connection conexao;

    public OcorrenciaDAOImpl(Connection conexao){
        this.conexao = conexao;
    }

    @Override
    public void inserir(Ocorrencia ocorrencia){




    }

}

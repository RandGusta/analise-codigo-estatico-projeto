package dao.impl;

import dao.OcorrenciaDAO;
import modelo.Ocorrencia;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.util.List;

public class OcorrenciaDAOImpl implements OcorrenciaDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("meuPC").createEntityManager();

    public OcorrenciaDAOImpl(){

    }

    @Override
    public void inserir(Ocorrencia ocorrencia){
    em.getTransaction().begin();
    em.persist(ocorrencia);
    em.getTransaction().commit();
    em.close();
    }


    @Override
    public Ocorrencia acharPorID(Long ID){
       return em.find(Ocorrencia.class, ID);
    }

    @Override
    public void deletar(Long ID){
        em.getTransaction().begin();
        Ocorrencia ocorrencia = em.find(Ocorrencia.class, ID);
        if(ocorrencia != null){
            em.remove(ocorrencia);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Ocorrencia> listar(){
        Query query = em.createQuery("SELECT o FROM Ocorrencia o");
        List<Ocorrencia> lista = query.getResultList();
        return lista;
    }


}

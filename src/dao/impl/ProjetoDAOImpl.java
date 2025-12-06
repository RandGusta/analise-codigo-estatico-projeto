package dao.impl;

import dao.ProjetoDAO;
import modelo.Projeto;

import javax.persistence.*;
import java.util.List;
import java.util.Queue;

public class ProjetoDAOImpl implements ProjetoDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("meuPC").createEntityManager();


    public ProjetoDAOImpl(){

    }


    @Override
    public void inserirProjeto(Projeto projeto){
        em.getTransaction().begin();
        em.persist(projeto);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deletarProjetoPorId(Long id){
        em.getTransaction().begin();
        Projeto projeto = em.find(Projeto.class, id);
        if(projeto != null){
            em.remove(projeto);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Projeto buscarProjetoPorId(Long id){
        TypedQuery<Projeto> query = em.createQuery("SELECT p FROM Projeto p WHERE p.id = :id", Projeto.class);
        query.setParameter("id", id);
        Projeto projeto = query.getSingleResult();
        return projeto;
    }

    @Override
    public List<Projeto> listarProjetos(){
        Query query = em.createQuery("SELECT p FROM Projeto p");
        List<Projeto> lista = query.getResultList();
        return lista;
    }





    @Override
    public List<Projeto> buscarProjetoPorUsuario(Long id){
        Query query = em.createQuery("SELECT p FROM Projeto p WHERE p.usuario.id LIKE :id");
        query.setParameter("id", id);
        List<Projeto> lista = query.getResultList();
        return lista;
    }

}

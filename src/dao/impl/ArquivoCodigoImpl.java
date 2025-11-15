package dao.impl;

import com.sun.xml.bind.v2.model.core.ID;
import dao.ArquivoCodigoDAO;
import modelo.ArquivoCodigo;

import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Queue;

public class ArquivoCodigoImpl implements ArquivoCodigoDAO {
    private EntityManager em;

    public ArquivoCodigoImpl(EntityManager em){
        this.em = em;
    }


    @Override
    public void adicionarArquivo(ArquivoCodigo arquivoCodigo){
        em.getTransaction().begin();
        em.persist(arquivoCodigo);
        em.getTransaction().commit();
    }

    @Override
    public void removerArquivoPorId(Long id){
        em.getTransaction().begin();
        ArquivoCodigo arquivoCodigo = em.find(ArquivoCodigo.class, id);
        if(arquivoCodigo != null){
            em.remove(arquivoCodigo);
        }
        em.getTransaction().commit();
    }

    @Override
    public List<ArquivoCodigo> buscarArquivoPorNome(String nome){
        Query query = em.createQuery("SELECT arq FROM ArquivoCodigo arq WHERE arq.nome LIKE :nome");
        query.setParameter("nome", "%"+nome+"%");
        List<ArquivoCodigo> lista = query.getResultList();
        return lista;
    }

    @Override
    public ArquivoCodigo buscarArquivoPorId(Long id){
        TypedQuery<ArquivoCodigo> query = em.createQuery("SELECT arq FROM ArquivoCodigo arq WHERE arq.id = :id", ArquivoCodigo.class);
        query.setParameter("id", id);
        ArquivoCodigo arquivoCodigo = query.getSingleResult();
        return arquivoCodigo;
    }

    @Override
    public List<ArquivoCodigo> listarArquivos(Long idProjeto){
        Query query = em.createQuery("SELECT arq FROM ArquivoCodigo arq");
        List<ArquivoCodigo> lista = query.getResultList();
        return lista;
    }



}

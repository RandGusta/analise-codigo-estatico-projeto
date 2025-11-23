package dao.impl;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Queue;

public class UsuarioDAOImpl implements UsuarioDAO {
    private EntityManager em;


    public UsuarioDAOImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void adicionarUsuario(Usuario usuario){
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    @Override
    public void deletarUsuarioPorId(Long id){
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, id);
        if(usuario != null){
            em.remove(usuario);
        }

        em.getTransaction().commit();
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id){
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :id", Usuario.class);
        query.setParameter("id", id);
        Usuario usuario = query.getSingleResult();
        return usuario;
    }

    @Override
    public List<Usuario> listarUsuario(){
        Query query = em.createQuery("SELECT u FROM Usuario u");
        List<Usuario> lista = query.getResultList();
        return lista;
    }


    @Override
    public Usuario buscarUsuarioPorEmail(String email){
        TypedQuery<Usuario> query = em.createQuery("SELECT U FROM Usuario WHERE u.email == :email", Usuario.class);
        query.setParameter("email", email);
        Usuario usuario = query.getSingleResult();
        return usuario;
    }

    @Override
    public Long contarUsuariosCadastrados(){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM Usuario u", Long.class);
        Long quantidade = query.getSingleResult();
        return quantidade;
    }


}

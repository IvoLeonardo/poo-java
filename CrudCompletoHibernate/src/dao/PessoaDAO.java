package dao;

import entity.PessoaEntity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PessoaDAO {
    protected EntityManager entityManager;

    public PessoaDAO() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WeHaveSciencePU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public PessoaEntity getById(final Long id) {
        return entityManager.find(PessoaEntity.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<PessoaEntity> findAll() {
        return entityManager.createQuery("FROM " + PessoaEntity.class.getName()).getResultList();
    }

    public void persist(PessoaEntity pessoa) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pessoa);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(PessoaEntity pessoa) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(pessoa);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(PessoaEntity pessoa) {
        try {
            entityManager.getTransaction().begin();
            pessoa = entityManager.find(PessoaEntity.class, pessoa.getId());
            entityManager.remove(pessoa);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final Long id) {
        try {
            PessoaEntity pessoa = getById(id);
            remove(pessoa);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
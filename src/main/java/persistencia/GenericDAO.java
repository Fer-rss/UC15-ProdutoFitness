package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public abstract class GenericDAO<T> implements BaseDAO<T> {

    private final Class<T> entityClass;

    protected GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEM() {
        return JPAUtil.getEntityManager();
    }

    @Override
    public void salvar(T entity) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public T buscarPorId(Long id) {
        return getEM().find(entityClass, id);
    }

    @Override
    public void atualizar(T entity) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public void excluir(T entity) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir " + entityClass.getSimpleName(), e);
        }
    }

    @Override
    public List<T> listarTodos() {
        EntityManager em = getEM();
        TypedQuery<T> query = em.createQuery(
                "SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
        return query.getResultList();
    }
}


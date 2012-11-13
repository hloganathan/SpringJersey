package net.cyberward.springjersey.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Implements the basic CRUD operations for a an entity type T. Only the entity
 * class and the entity manager need to be provided in an implementing class.
 */
public abstract class AbstractBaseDAO<T extends AbstractBaseEntity> implements BaseDAO<T> {

    protected abstract Class<T> getEntityClass();

    protected abstract EntityManager getEntityManager();

    @Override
    public void create(T entity) {
	getEntityManager().persist(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T read(final long id) {
	// using this instead of just em.read so we get exception
	T entity;
	Query query = getEntityManager().createQuery(
		"Select h from " + getEntityClass().getSimpleName() + " h where h.id = :id");
	query.setParameter("id", id);
	entity = (T) query.getSingleResult();
	return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> readListOf() {
	List<T> list = getEntityManager().createQuery("from " + getEntityClass().getName())
		.getResultList();
	return list;
    }

    @Override
    public void update(final T entity) {
	T persistedEntity = read(entity.getId());
	persistedEntity.merge(entity);
    }

    @Override
    public void delete(final long id) {
	T entity = read(id);
	getEntityManager().remove(entity);
    }

}

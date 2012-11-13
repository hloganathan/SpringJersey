package net.cyberward.springjersey.repository;

import java.util.List;

/**
 * Interface for basic crud operations.
 * T is the specific type of entity.
 */
public interface BaseDAO<T extends AbstractBaseEntity> {

	void create(T entity);

	T read(long id);

	List<T> readListOf();

	void update(T entity);

	void delete(long id);

}
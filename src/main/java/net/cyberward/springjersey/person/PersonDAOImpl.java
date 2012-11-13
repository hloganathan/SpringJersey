package net.cyberward.springjersey.person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.cyberward.springjersey.repository.AbstractBaseDAO;

import org.springframework.stereotype.Repository;


/**
 * Data Access Layer.
 * Basic CRUD operations can be handled in the base class.
 */
@Repository
public class PersonDAOImpl extends AbstractBaseDAO<PersonEntity> implements PersonDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected Class<PersonEntity> getEntityClass() {
		return PersonEntity.class;
	}

}

package net.cyberward.springjersey.person;

import net.cyberward.springjersey.repository.BaseDAO;

/**
 * Provide specific interface for the PersonDAO
 * CRUD operations will come from the BaseDAO
 */
public interface PersonDAO extends BaseDAO<PersonEntity> {

}
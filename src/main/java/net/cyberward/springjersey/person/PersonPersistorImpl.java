package net.cyberward.springjersey.person;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *  DB Transactions implemented here.
 *  DTO translated into entity objects.
 */
@Component
public class PersonPersistorImpl implements PersonPersistor {

	@Resource
	private PersonDAO persistor;

	@Override
	@Transactional(value = "transactionManager", readOnly = false, rollbackFor = Throwable.class)
	public long createPerson(final PersonDTO person) {
		PersonEntity entity = new PersonEntity(person);
		persistor.create(entity);
		return entity.getId();
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public PersonDTO getPerson(final long id) {
		PersonEntity entity = persistor.read(id);
		PersonDTO person = new PersonDTO(entity);
		return person;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<PersonDTO> getListOfPeople() {
		List<PersonEntity> entityList = persistor.readListOf();
		List<PersonDTO> personList = new ArrayList<PersonDTO>();
		for (PersonEntity entity : entityList) {
			PersonDTO person = new PersonDTO(entity);
			personList.add(person);
		}
		return personList;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false, rollbackFor = Throwable.class)
	public void updatePerson(final PersonDTO person) {
		PersonEntity entity = new PersonEntity(person);
		persistor.update(entity);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false, rollbackFor = Throwable.class)
	public void deletePerson(final long id) {
		persistor.delete(id);
	}

}

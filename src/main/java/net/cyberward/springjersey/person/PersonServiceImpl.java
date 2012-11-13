package net.cyberward.springjersey.person;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 *  Would implement XA transactions here if needed.
 *  Persistor Aggregation.
 *  This example is just pass-through methods.
 */
@Component
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonPersistor persistor;

    @Override
    public long createPerson(final PersonDTO person) {
	return persistor.createPerson(person);
    }

    @Override
    public PersonDTO getPerson(final long id) {
	return persistor.getPerson(id);
    }

    @Override
    public List<PersonDTO> getListOfPeople() {
	return persistor.getListOfPeople();
    }

    @Override
    public void updatePerson(final PersonDTO person) {
	persistor.updatePerson(person);
    }

    @Override
    public void deletePerson(final long id) {
	persistor.deletePerson(id);
    }

}

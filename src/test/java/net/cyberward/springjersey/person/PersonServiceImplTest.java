package net.cyberward.springjersey.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import net.cyberward.springjersey.person.PersonDAO;
import net.cyberward.springjersey.person.PersonDTO;
import net.cyberward.springjersey.person.PersonEntity;
import net.cyberward.springjersey.person.PersonServiceImpl;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PersonServiceImplTest {

	@InjectMocks
	private final PersonServiceImpl service = new PersonServiceImpl();

	@Mock
	PersonDAO mockPersistor;

	@Captor
	private ArgumentCaptor<PersonEntity> entityCaptor;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreate() {
		PersonDTO person = new PersonDTO();
		person.setFirstName("first");
		person.setLastName("last");
		person.setId(123);

		long id = service.createPerson(person);

		verify(mockPersistor).create(entityCaptor.capture());
		PersonEntity entity = entityCaptor.getValue();

		assertEquals(0, id); // generated value
		assertEquals("first", entity.getFirstName());
		assertEquals("last", entity.getLastName());
	}

	@Test
	public void testGetPerson() throws Exception {
		PersonEntity entity = new PersonEntity();
		entity.setFirstName("first");
		entity.setLastName("last");
		FieldUtils.writeDeclaredField(entity, "id", 123, true);

		when(mockPersistor.read(anyLong())).thenReturn(entity);

		PersonDTO person = service.getPerson(123);

		verify(mockPersistor).read(123);

		assertNotNull(person);
		assertEquals("first", person.getFirstName());
		assertEquals("last", person.getLastName());
		assertEquals(123, person.getId());
	}

	@Test
	public void testGetListOfPeople() throws IllegalAccessException {
		PersonEntity entity1 = new PersonEntity();
		entity1.setFirstName("first1");
		entity1.setLastName("last1");
		FieldUtils.writeDeclaredField(entity1, "id", 1, true);

		PersonEntity entity2 = new PersonEntity();
		entity2.setFirstName("first2");
		entity2.setLastName("last2");
		FieldUtils.writeDeclaredField(entity2, "id", 2, true);

		when(mockPersistor.readListOf()).thenReturn(
				Arrays.asList(entity1, entity2));

		List<PersonDTO> personList = service.getListOfPeople();

		verify(mockPersistor).readListOf();

		assertNotNull(personList);
		assertEquals(2, personList.size());

		assertNotNull(personList.get(0));
		assertEquals("first1", personList.get(0).getFirstName());
		assertEquals("last1", personList.get(0).getLastName());
		assertEquals(1, personList.get(0).getId());

		assertNotNull(personList.get(1));
		assertEquals("first2", personList.get(1).getFirstName());
		assertEquals("last2", personList.get(1).getLastName());
		assertEquals(2, personList.get(1).getId());
	}

	@Test
	public void testUpdatePerson() {

		PersonDTO person = new PersonDTO();
		person.setFirstName("Chris");
		person.setLastName("Ward");

		PersonEntity personEntity = new PersonEntity();
		personEntity.setFirstName("John");
		personEntity.setLastName("Wayne");

		when(mockPersistor.read(anyLong())).thenReturn(personEntity);
		service.updatePerson(person);

		verify(mockPersistor).update(entityCaptor.capture());

		PersonEntity entity = entityCaptor.getValue();

		assertEquals("Chris", entity.getFirstName());
		assertEquals("Ward", entity.getLastName());
	}

	@Test
	public void testDeletePerson() {
		service.deletePerson(1);

		verify(mockPersistor).delete(1);
	}

}

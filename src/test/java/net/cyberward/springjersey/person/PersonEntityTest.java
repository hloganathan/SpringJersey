package net.cyberward.springjersey.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import net.cyberward.springjersey.person.PersonDTO;
import net.cyberward.springjersey.person.PersonEntity;

import org.junit.Test;

public class PersonEntityTest {

	@Test
	public void testPersonEntity_null()
	{
		PersonDTO person = null;
		PersonEntity entity = new PersonEntity(person);
		
		assertNotNull(entity);
		assertNull(entity.getFirstName());
		assertNull(entity.getLastName());
		assertEquals(0, entity.getId());
	}
	
	@Test
	public void testPersonEntity()
	{
		PersonDTO person = new PersonDTO();
		person.setFirstName("Karen");
		person.setLastName("Johnson");
		person.setId(1);
		
		PersonEntity entity = new PersonEntity(person);
		
		assertNotNull(entity);
		assertEquals("Karen", entity.getFirstName());
		assertEquals("Johnson", entity.getLastName());
		assertEquals(1, entity.getId());
	}
}

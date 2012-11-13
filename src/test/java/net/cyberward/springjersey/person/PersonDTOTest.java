package net.cyberward.springjersey.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import net.cyberward.springjersey.person.PersonDTO;
import net.cyberward.springjersey.person.PersonEntity;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

public class PersonDTOTest {

	@Test
	public void testPerson_constructor_null() {
		PersonEntity entity = null;
		PersonDTO person = new PersonDTO(entity);

		assertNotNull(person);
		assertNull(person.getFirstName());
		assertNull(person.getLastName());
		assertNull(person.getModifiedDate());
	}

	@Test
	public void testPerson_constructor() throws Exception {
		PersonEntity entity = new PersonEntity();
		entity.setFirstName("Karen");
		entity.setLastName("Johnson");
		FieldUtils.writeField(entity, "id", 1, true);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		FieldUtils.writeField(entity, "modifiedDate", timestamp, true);

		PersonDTO person = new PersonDTO(entity);
		assertEquals("Karen", person.getFirstName());
		assertEquals("Johnson", person.getLastName());
		assertEquals(1, person.getId());
		assertEquals(entity.getModifiedDate(), person.getModifiedDate());
	}

	@Test
	public void testPerson_merge() {
		PersonEntity entity = new PersonEntity();
		entity.setFirstName("Chris");
		entity.setLastName("Ward");
		
		PersonEntity newEntity = new PersonEntity();
		newEntity.setFirstName("John");
		newEntity.setLastName("Wayne");
		
		entity.merge(newEntity);

		assertEquals("John", entity.getFirstName());
		assertEquals("Wayne", entity.getLastName());
	}
	
	@Test
	public void testPerson_constraints_pass() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		
		PersonDTO person = new PersonDTO();
		person.setFirstName("Joe");
		person.setLastName("Shoeless");
		
		Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
		assertNotNull(violations);
		assertEquals(0, violations.size());
	}
	
	@Test
	public void testPerson_constraints_no_names() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		
		PersonDTO person = new PersonDTO();
		
		Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
		assertNotNull(violations);
		assertEquals(1, violations.size());
	}
	
	@Test
	public void testPerson_constraints_long_names() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		
		PersonDTO person = new PersonDTO();
		person.setFirstName("123456789012345678901");
		person.setLastName("123456789012345678901");
		
		Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
		assertNotNull(violations);
		assertEquals(2, violations.size());
	}
	
}

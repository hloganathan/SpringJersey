package net.cyberward.springjersey.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import net.cyberward.springjersey.config.AppConfig;
import net.cyberward.springjersey.person.PersonController;
import net.cyberward.springjersey.person.PersonDTO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Currently throws an exception because the jersey UriInfo class is not defined in Spring. 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class PersonControllerIntegrationTest {

	@Resource
	private PersonController controller;

	private PersonDTO person1;
	private PersonDTO person2;
	
	@Before
	public void setup() {
		person1 = new PersonDTO();
		person1.setFirstName("Chris");
		person1.setLastName("Ward");
		
		person2 = new PersonDTO();
		person2.setFirstName("Joe");
		person2.setLastName("Shoeless");
	}

	@Test
	public void testPerson() {

		controller.createPerson(person1);
		controller.createPerson(person2);
		List<PersonDTO> allPeople = controller.getListOfPeople();
		
		assertNotNull(allPeople);
		assertEquals(2, allPeople.size());
	}
}

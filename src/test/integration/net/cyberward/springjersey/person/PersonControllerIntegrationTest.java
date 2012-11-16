package net.cyberward.springjersey.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ws.rs.core.MediaType;

import net.cyberward.springjersey.BaseControllerIntegrationTest;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class PersonControllerIntegrationTest extends BaseControllerIntegrationTest {

    private PersonDTO person1;
    private PersonDTO person2;

    public PersonControllerIntegrationTest() {
	super("net.cyberward.springjersey.person");
    }

    @Override
    @Before
    public void setUp() throws Exception {
	super.setUp();
	person1 = new PersonDTO();
	person1.setFirstName("Jim");
	person1.setLastName("Bean");

	person2 = new PersonDTO();
	person2.setFirstName("Jimmy");
	person2.setLastName("Johns");
    }

    @Test
    public void testStoreRetrievePerson() {
	WebResource webResource = resource();
	webResource.path("person").accept(MediaType.APPLICATION_XML).post(person1);

	PersonDTO response = webResource.path("person/1").accept(MediaType.APPLICATION_XML).get(PersonDTO.class);
	assertNotNull(response);
	assertEquals(person1.getFirstName(), response.getFirstName());
	assertEquals(person1.getLastName(), response.getLastName());
    }

    @Test
    public void testGetPeople() {
	WebResource webResource = resource();
	GenericType<List<PersonDTO>> personListType = new GenericType<List<PersonDTO>>() {
	};
	webResource.path("person").accept(MediaType.APPLICATION_XML).post(person1);
	webResource.path("person").accept(MediaType.APPLICATION_XML).post(person2);
	
	List<PersonDTO> response = webResource.path("person").accept(MediaType.APPLICATION_XML).get(personListType);
	assertNotNull(response);
	assertEquals(2, response.size());
    }
}

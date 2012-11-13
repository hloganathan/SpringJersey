package net.cyberward.springjersey.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.MediaType;

import net.cyberward.springjersey.person.PersonDTO;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

/**
 * Attempt at jersey integration test. Issue is that spring doesn't start
 * up, so no injection happens.
 */
public class PersonControllerJerseyIntegrationTest extends JerseyTest {

    public PersonControllerJerseyIntegrationTest() throws Exception {
        super(new WebAppDescriptor.Builder("net.cyberward.springjersey")
                .contextPath("jerseyspring").build());
    }

    @Test
    public void testStorePerson() {
        WebResource webResource = resource();
        PersonDTO person = new PersonDTO();
        person.setFirstName("Kathy");
        person.setLastName("Johnson");
		webResource.path("person").accept(MediaType.APPLICATION_XML).post(person);
    }
    
    @Test
    public void testGetPerson() {
    	WebResource webResource = resource();
    	PersonDTO response = webResource.path("person/1").accept(MediaType.APPLICATION_XML).get(PersonDTO.class);
        assertNotNull(response);
        assertEquals("Kathy", response.getFirstName());
        assertEquals("Johnson", response.getLastName());
    }
}

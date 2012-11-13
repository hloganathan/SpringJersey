package net.cyberward.springjersey.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;

import javax.validation.Validator;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import net.cyberward.springjersey.person.PersonController;
import net.cyberward.springjersey.person.PersonDTO;
import net.cyberward.springjersey.person.PersonService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PersonControllerTest {

	@InjectMocks
	private final PersonController controller = new PersonController();

	@Mock
	private PersonService mockService;

	@Mock
	private UriInfo mockUriInfo;

	@Mock
	private UriBuilder mockUriBuilder;

	@Mock
	private Validator mockValidator;

	@Captor
	private ArgumentCaptor<PersonDTO> personCaptor;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
		public void testGetPerson() {
			controller.getPerson(1);
	
			verify(mockService).getPerson(1);
		}

	@Test
		public void testGetListOfPeople() {
			controller.getListOfPeople();
	
			verify(mockService).getListOfPeople();
		}

	@Test
	public void testUpdatePerson() {
		PersonDTO person = new PersonDTO();
		controller.updatePerson(person);

		verify(mockService).updatePerson(person);
	}

	@Test
	public void testDeletePerson() {
		controller.deletePerson(1);
		
		verify(mockService).deletePerson(1);
	}

	@Test
	public void testCreatePerson() throws Exception {
		PersonDTO person = new PersonDTO();
		person.setFirstName("first");
		person.setLastName("last");

		URI uri = new URI("/test/person/1");
		when(mockUriInfo.getAbsolutePathBuilder()).thenReturn(mockUriBuilder);
		when(mockUriBuilder.path(anyString())).thenReturn(mockUriBuilder);
		when(mockUriBuilder.build()).thenReturn(uri);

		Response response = controller.createPerson(person);

		verify(mockService).createPerson(personCaptor.capture());

		assertNotNull(response);
		assertEquals(201, response.getStatus());

		String location = response.getMetadata().getFirst("Location")
				.toString();
		assertTrue(location.endsWith("/test/person/1"));
	}

}

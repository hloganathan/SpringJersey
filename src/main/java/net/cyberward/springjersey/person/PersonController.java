package net.cyberward.springjersey.person;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import net.cyberward.springjersey.exception.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This controller supports CRUD operations on the PERSON table.
 * This is a JAX-RS based controller.
 * URI is at /person.
 * Either XML or JSON are supported. 
 */
@Component
@Path("/person")
@Scope("singleton")
@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class PersonController {

	private static final Logger logger = LoggerFactory
			.getLogger(PersonController.class);

	@Context
	private UriInfo uriInfo;

	@Resource
	private PersonService service;

	@Resource
	private Validator validator;

	/**
	 * Retrieves a person based on an id.
	 * URI is GET:/person/{id}
	 * @param personId - the id on the DB of the person
	 * @return a Person
	 */
	@GET
	@Path("/{id}")
	public PersonDTO getPerson(@PathParam("id") long personId) {
		logger.debug("looking up person " + personId);
		PersonDTO person = service.getPerson(personId);
		return person;
	}
	
	/**
	 * Retrieves a list of all people.
	 * URI is GET:/person
	 * @return a list of Person
	 */
	@GET
	public List<PersonDTO> getListOfPeople() {
		logger.debug("getting all people");
		List<PersonDTO> personList = service.getListOfPeople();
		logger.debug("found: " + (personList == null ? 0 : personList.size()));
		return personList;
	}

	/**
	 * Adds a Person to the DB.
	 * A lastName is required.
	 * URI is POST:/person
	 * @param person to add.
	 */
	@POST
	public Response createPerson(PersonDTO person) {
		logger.debug("storing person: " + person.getLastName() + ", "
				+ person.getFirstName());
		
		Set<ConstraintViolation<PersonDTO>> validations = validator.validate(person);
		if(validations!=null && validations.size()>0){
		    throw new ValidationException(validations);
		}
		
		
		String personId = String.valueOf(service.createPerson(person));
		logger.debug("created id: " + personId);
		URI location = uriInfo.getAbsolutePathBuilder().path(personId).build();
		Response response = Response.created(location).build();
		return response;
	}

	/**
	 * Updates a Person on the DB.
	 * The entire person must be sent. The id should be of an existing person.
	 * URI is PUT:/person
	 * @param person for update.
	 */
	@PUT
	public void updatePerson(PersonDTO person) {
		logger.debug("update person");
		service.updatePerson(person);
	}
	
	/**
	 * Delete a person from the DB.
	 * URI is DELETE:/person/{id}
	 * @param id of person to delete.
	 */
	@DELETE
	@Path("/{id}")
	public void deletePerson(long id) {
		logger.debug("deleting person: " + id);
		service.deletePerson(id);
	}

}

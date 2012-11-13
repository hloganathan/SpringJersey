package net.cyberward.springjersey.person;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.cyberward.springjersey.repository.AbstractBaseDTO;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Helps the framework translate XML or json into an object.
 * Provides input field validation.
 * Constructor translates to an entity.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "person")
public class PersonDTO extends AbstractBaseDTO {

	@Size(max = 20)
	@XmlElement
	private String firstName;

	@NotEmpty
	@Size(max = 20)
	@XmlElement
	private String lastName;

	@XmlElement
	private long id;

	public PersonDTO() {
	}

	public PersonDTO(final PersonEntity entity) {
		super(entity);
		if (entity != null) {
			this.firstName = entity.getFirstName();
			this.lastName = entity.getLastName();
			this.id = entity.getId();
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

}

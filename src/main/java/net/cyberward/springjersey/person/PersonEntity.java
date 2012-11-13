package net.cyberward.springjersey.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.cyberward.springjersey.repository.AbstractBaseEntity;

/**
 * Provides an entity that maps fields to a column on a table.
 * DB constraints are identified here.
 * The constructor converts to a DTO.
 * The merge method is implemented to assist in an update.
 */
@Entity
@Table(name = "person")
public class PersonEntity extends AbstractBaseEntity {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "first_name", length = 32, unique = false)
	private String firstName;

	@Column(name = "last_name", length = 32, unique = false)
	private String lastName;

	public PersonEntity() {
	}

	@Override
	public long getId() {
		return id;
	}

	public PersonEntity(final PersonDTO person) {
		if (person != null) {
			this.firstName = person.getFirstName();
			this.lastName = person.getLastName();
			this.id = person.getId();
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

	/**
	 * Need to populate the fields that could have changed.
	 */
	@Override
	public void merge(final AbstractBaseEntity newEntity) {
		if (newEntity != null) {
			PersonEntity newPersonEntity = (PersonEntity) newEntity;
			this.firstName = newPersonEntity.getFirstName();
			this.lastName = newPersonEntity.getLastName();
		}
	}

}

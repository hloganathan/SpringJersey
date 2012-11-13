package net.cyberward.springjersey.repository;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

/**
 * Base DTO provides the modified date on all DTO's.
 */
public abstract class AbstractBaseDTO {

	@XmlElement
	private Date modifiedDate;

	public AbstractBaseDTO() {
	}

	public AbstractBaseDTO(final AbstractBaseEntity baseEntity) {
		if (baseEntity != null) {
			this.modifiedDate = (Date) baseEntity.getModifiedDate();
		}
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

}

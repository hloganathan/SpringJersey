package net.cyberward.springjersey.repository;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Base entity provides the modified date on all entities. This class auto
 * populates the modified date on the database when the entity is created or
 * modified.
 */
@MappedSuperclass
public abstract class AbstractBaseEntity {

    @Column(name = "MODIFIED_DATE", nullable = false)
    private Timestamp modifiedDate;

    public AbstractBaseEntity() {
    }

    public abstract long getId();

    public abstract void merge(AbstractBaseEntity newEntity);

    @PrePersist
    protected final void onCreate() {
	this.modifiedDate = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected final void onUpdate() {
	this.modifiedDate = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getModifiedDate() {
	return modifiedDate;
    }

}

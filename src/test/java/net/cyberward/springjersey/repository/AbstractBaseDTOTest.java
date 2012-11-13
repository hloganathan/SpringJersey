package net.cyberward.springjersey.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import net.cyberward.springjersey.repository.AbstractBaseDTO;
import net.cyberward.springjersey.repository.AbstractBaseEntity;

import org.junit.Test;

public class AbstractBaseDTOTest {

	@Test
	public void testOnCreate() {
		TestEntity entity = new TestEntity();
		assertNull(entity.getModifiedDate());
		entity.onCreate();
		
		assertNotNull(entity.getModifiedDate());
	}

	@Test
	public void testOnUpdate() {
		TestEntity entity = new TestEntity();
		assertNull(entity.getModifiedDate());
		entity.onUpdate();
		
		assertNotNull(entity.getModifiedDate());
	}
	
	@Test
	public void testConstructor() throws Exception {
		TestEntity entity = new TestEntity();
		entity.onCreate();
		
		TestDTO domain = new TestDTO(entity);
		
		assertNotNull(domain.getModifiedDate());
		assertEquals(entity.getModifiedDate(), domain.getModifiedDate());
	}
	
	public class TestEntity extends AbstractBaseEntity {
		public TestEntity() {
			super();
		}

		@Override
		public long getId() {
			return 0;
		}

		@Override
		public void merge(AbstractBaseEntity newEntity) {
		}
	}

	public class TestDTO extends AbstractBaseDTO {
		public TestDTO() {
			super();
		}
		public TestDTO(TestEntity entity) {
			super(entity);
		}
	}
}

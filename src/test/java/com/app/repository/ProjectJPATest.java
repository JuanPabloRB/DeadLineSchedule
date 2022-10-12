package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.model.Schedule;

/**
 * ProjectJPATest 
 * Assumptions: 
 * Not all the tests will be created, only some since it is a test
 * 
 * @author Juan Pablo Rodriguez Bianchi
 * @version 1.0
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProjectJPATest {

	@Autowired
	IScheduleRepository repo;

	@Test
	public void save() {
		
		Schedule sc = new Schedule(1200, true);
		sc = repo.save(sc);
		assertNotNull(sc);
		assertNotNull(sc.getIdentifier());
		assertEquals(1200, sc.getDeadlineMs());

	}

	@Test
	public void delete() {
		
		Schedule sc = new Schedule(1200, true);
		sc = repo.save(sc);		
		assertNotNull(sc);
		assertNotNull(sc.getIdentifier());
		assertEquals(1200, sc.getDeadlineMs());
		repo.deleteById((long) 1);
		boolean exist = repo.existsById((long) 1);
		assertEquals(false, exist);

	}

}

package com.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.model.Schedule;
import com.app.repository.IScheduleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
class DeadLineEngineImplTest {

	@Autowired
	IScheduleRepository repo;
	
	@Test
	void scheduleTest() {
		DeadLineEngineImpl deadLineEngineImpl = new DeadLineEngineImpl();
		long deadlineMs = 1000;

		deadLineEngineImpl.schedule(deadlineMs);
		
		//assertNotNull(identifier);
		//assertNotNull(identifier);
		//assertEquals(1, identifier);

	}

	
	@Test
	void cancelTest() {
		
		DeadLineEngineImpl deadLineEngineImpl = new DeadLineEngineImpl();
		long identifier = 1;
		
		Schedule sc = new Schedule(1200, true);
		sc = repo.save(sc);
		assertNotNull(sc);
		assertNotNull(sc.getIdentifier());
		assertEquals(1200, sc.getDeadlineMs());

		deadLineEngineImpl.cancel(identifier);
		
		boolean exist = repo.existsById((long) 1);

		assertEquals(false, exist);
	}
	
	
	@Test
	void pollTest() {
		
		DeadLineEngineImpl deadLineEngineImpl = new DeadLineEngineImpl();
		long identifier = 1;
		
		Schedule sc = new Schedule(1200, true);
		sc = repo.save(sc);
		
		
		assertNotNull(sc);
		assertNotNull(sc.getIdentifier());
		assertEquals(1200, sc.getDeadlineMs());

		deadLineEngineImpl.cancel(identifier);
		
		boolean exist = repo.existsById((long) 1);

		assertEquals(false, exist);
	}
}

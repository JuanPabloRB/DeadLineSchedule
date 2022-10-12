package com.app.service;

import static org.mockito.Mockito.when;

import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.repository.IScheduleRepository;

/**
 * DeadLineEngineImplTest 
 * Assumptions: 
 * Not all the tests will be created, only some since it is a test
 * 
 * @author Juan Pablo Rodriguez Bianchi
 * @version 1.0
 */

public class DeadLineEngineImplTest {

	@Mock
	private IScheduleRepository repo;

	@InjectMocks
	private DeadLineEngineImpl deadLineEngineImpl;

	private long deadlineMs;
	private long identifier;
	// private int intValue;
	// private int maxPoll;
	//private Consumer<Long> handler;

	@BeforeEach
	void setUp() {

		MockitoAnnotations.openMocks(this);

	}

	@Test
	public void testSchedule() {
		when(deadLineEngineImpl.schedule(deadlineMs)).thenReturn(identifier);

	}

	@Test
	public void testCancel() {
		when(deadLineEngineImpl.cancel(identifier)).thenReturn(true);
	}

	/*
	 * @Test public void testSize() {
	 * 
	 * intValue = 0; when(deadLineEngineImpl.size()).thenReturn(intValue);
	 * 
	 * }
	 * 
	 * @Test public void testPoll() { intValue = 0;
	 * when(deadLineEngineImpl.poll(deadlineMs, handler,
	 * maxPoll)).thenReturn(intValue); }
	 */

}

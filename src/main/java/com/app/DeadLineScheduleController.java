package com.app;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.MyException;
import com.app.service.DeadLineEngineImpl;
import com.app.util.RequestServiceParam;

/**
 * Represents Controller of Apllication
 * 
 * @author Juan Pablo Rodriguez Bianchi
 * @version 1.0
 */

@RestController
@RequestMapping("/deadline")
public class DeadLineScheduleController {

	@Autowired
	DeadLineEngineImpl deadLineEngineImpl;

	/**
	 * User for insert new deadLine in the schedule
	 * 
	 * @param deadlineMs the milliseconds
	 * @return An identifier for the scheduled deadline.
	 * @throws MyException 
	 */

	@PutMapping("/schedule")
	@ResponseStatus(HttpStatus.OK)
	public long newTimeLineschedule(@RequestParam(value = "deadlineMs", required = true) long deadlineMs) throws MyException {
		try {
			return deadLineEngineImpl.schedule(deadlineMs);
		} catch (Exception e) {
			throw new MyException("ERROR METHOD schedule CLASS DeadLineScheduleController ", e.getCause());
	
		} 
	}

	/**
	 * Use for cancel and delete dead line
	 * 
	 * @param requestId identifier to cancel.
	 * @return true if canceled.
	 * @throws MyException 
	 */

	@DeleteMapping("/cancel")
	@ResponseStatus(HttpStatus.OK)
	public boolean cancel(@RequestParam(value = "requestId", required = true) long identifier) throws MyException {

		try {
			return deadLineEngineImpl.cancel(identifier);

		} catch (Exception e) {
			throw new MyException("ERROR METHOD cancel CLASS DeadLineScheduleController ", e.getCause());
		}
	}

	/**
	 * Use for count All Register deadline including the active and inactive
	 * 
	 * @return the number of registered deadlines.
	 * @throws MyException 
	 */
	@GetMapping("/countRegisterDeadLines")
	public int countDeadLines() throws MyException {
		try {
			
			return deadLineEngineImpl.size();

		} catch (Exception e) {
			throw new MyException("ERROR METHOD countRegisterDeadLines CLASS DeadLineScheduleController ",
					e.getCause());
		} 
	}

	/**
	 * Use for count the deadline active expired to date, when finish the count update this to inactive 
	 * Assumptions:
	 * Once the query is made, they will be changed to state = false
	 * They will not be removed from the consulted times
	
	 * @param nowMs   time in milliseconds since epoch to check deadlines against.
	 * @param handler to call with identifier of expired deadlines.
	 * @param maxPoll count of maximum number of expired deadlines to process.
	 * @return number of expired deadlines that fired successfully.
	 * @throws MyException 
	 */

	@PostMapping("/pollRequest")
	public int pollRequest(@RequestBody RequestServiceParam requestServiceParam) throws MyException {

		try {
			Consumer<Long> conciseLambda = t -> System.out.println("Identifier:" + t);

			final var listOfLongs = requestServiceParam.getListHandler();

			listOfLongs.forEach(conciseLambda);

			return deadLineEngineImpl.poll(requestServiceParam.getNowMs(), conciseLambda,
					requestServiceParam.getIntMaxPoll());

		} catch (Exception e) {
			throw new MyException("ERROR METHOD pollRequest CLASS DeadLineScheduleController ", e.getCause());
		}

	}
}

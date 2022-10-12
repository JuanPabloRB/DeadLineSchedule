package com.app.service;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Schedule;
import com.app.repository.IScheduleRepository;

/**
 * Implements IDeadLineEngine required for the proyect
 * 
 * @author Juan Pablo Rodriguez Bianchi
 * @version 1.0
 */

@Service
@Transactional
public class DeadLineEngineImpl implements IDeadLineEngine {

	@Autowired
	IScheduleRepository repo;

	public long schedule(long deadlineMs) {

		Schedule sc = new Schedule();
		sc.setDeadlineMs(deadlineMs);
		sc.setActive(true);

		repo.save(sc);
		return sc.getIdentifier();

	}

	public boolean cancel(long requestId) {

		boolean exist = repo.existsById(requestId);

		if (exist) {
			repo.deleteById(requestId);
			return exist;
		} else {
			return false;
		}
	}

	public int size() {
		return (int) repo.count();
	}

	public int poll(long nowMs, Consumer<Long> handler, int maxPoll) {

		Pageable limit = PageRequest.of(0, maxPoll);
		int countExpired = 0;

		List<Schedule> listSchedule = repo.findByDeadlineMsLessThanEqualAndActive(nowMs, true, limit);

		countExpired = listSchedule.size();

		for (Schedule s : listSchedule)
			repo.setScheduleByActiveFor(s.getIdentifier());

		listSchedule.stream().map(s -> s.getIdentifier()).forEach(handler);

		return countExpired;

	}

}

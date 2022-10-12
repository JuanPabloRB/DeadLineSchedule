package com.app.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Schedule;

/**
 * Employee JPA Interface
 * 
 * @author Juan Pablo Rodriguez Bianchi
 * @version 1.0
 */
@Repository
public interface IScheduleRepository extends JpaRepository<Schedule, Long> {

	List<Schedule> findByDeadlineMsLessThanEqualAndActive(long nowMs, boolean active, Pageable pageable);

	@Modifying
	@Query("UPDATE Schedule s SET s.active = false WHERE s.identifier = ?1")
	void setScheduleByActiveFor(long identifier);

}

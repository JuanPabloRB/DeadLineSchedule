package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents an Schedule entity
 * 
 * @author Juan Pablo Rodriguez Bianchi
 * @version 1.0
 */

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long identifier;

	@Column(nullable = false)
	private long deadlineMs;

	@Column(nullable = false)
	private boolean active;

	public Schedule(long deadlineMs, boolean active) {
		super();
		this.deadlineMs = deadlineMs;
		this.active = active;
	}

	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(long identifier) {
		this.identifier = identifier;
	}

	public long getDeadlineMs() {
		return deadlineMs;
	}

	public void setDeadlineMs(long deadlineMs) {
		this.deadlineMs = deadlineMs;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}

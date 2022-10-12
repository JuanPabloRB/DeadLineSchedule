package com.app.util;

import java.util.List;

/**
 * Use for mapping request body for the method pollRequest in the class DeadLineScheduleController
 * @author Juan Pablo Rodriguez Bianchi
 * @version 1.0
 */

public class RequestServiceParam {

	private long nowMs;
	private int intMaxPoll;
	private List<Long> listHandler;

	public long getNowMs() {
		return nowMs;
	}

	public void setNowMs(long nowMs) {
		this.nowMs = nowMs;
	}

	public int getIntMaxPoll() {
		return intMaxPoll;
	}

	public void setIntMaxPoll(int intMaxPoll) {
		this.intMaxPoll = intMaxPoll;
	}

	public List<Long> getListHandler() {
		return listHandler;
	}

	public void setListHandler(List<Long> listHandler) {
		this.listHandler = listHandler;
	}



	

}

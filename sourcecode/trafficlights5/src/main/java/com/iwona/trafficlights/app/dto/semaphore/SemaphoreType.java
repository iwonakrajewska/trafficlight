package com.iwona.trafficlights.app.dto.semaphore;

public enum SemaphoreType {

	MAIN_STREET(Integer.valueOf(1)), 
	SIDE_STREET(Integer.valueOf(2));

	private Integer semaphoreTypeId;

	SemaphoreType(Integer semaphoreTypeId) {
		this.semaphoreTypeId = semaphoreTypeId;
	}

	public Integer getId() {
		return semaphoreTypeId;
	}

}

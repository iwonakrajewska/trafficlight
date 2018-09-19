package com.iwona.trafficlights.app.dto.semaphore;

import org.springframework.stereotype.Component;

@Component
public class SS22 extends Semaphore {

	private final SemaphoreType semaphoreType = SemaphoreType.SIDE_STREET;
	private final String semaphoreName = "S22";

	@Override
	SemaphoreType getSemaphoreType() {
		return semaphoreType;
	}

	@Override
	String getSemaphoreName() {
		return semaphoreName;
	}

}

package com.iwona.trafficlights.app.dto.semaphore;

import org.springframework.stereotype.Component;

@Component
public class SM12 extends Semaphore {

	private final SemaphoreType semaphoreType = SemaphoreType.MAIN_STREET;
	private final String semaphoreName = "M12";

	@Override
	SemaphoreType getSemaphoreType() {
		return semaphoreType;
	}

	@Override
	String getSemaphoreName() {
		return semaphoreName;
	}
	
}

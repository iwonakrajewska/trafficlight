package com.iwona.trafficlights.app.scheduler;

import com.iwona.trafficlights.app.dto.semaphore.Semaphore;

public interface SemaphoreWatch {

	String visit(Semaphore semaphore);

}

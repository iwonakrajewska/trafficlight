package com.iwona.trafficlights.task3;

import com.iwona.trafficlights.app.dto.semaphore.SM11;
import com.iwona.trafficlights.app.manager.LightManager;
import com.iwona.trafficlights.app.scheduler.SemaphoreVisitor;
import com.iwona.trafficlights.config.startupinit.SemaphoreRegistry;


public class SemaphoreRegistryTask3 extends SemaphoreRegistry {

	private final LightManager lightManager;
	private final SemaphoreVisitor semaphoreVisitor;
	private final SM11 sm11;


	public SemaphoreRegistryTask3(LightManager lightManager, SemaphoreVisitor semaphoreVisitor, SM11 sm11) {
		this.lightManager = lightManager;
		this.semaphoreVisitor = semaphoreVisitor;
		this.sm11 = sm11;
	}

	public void registerSemaphores() {
		lightManager.addPropertyChangeListener(sm11);
	}
	
	public void registerSemaphoreVisitor() {
		semaphoreVisitor.addSemaphoreToWatch(sm11);
	}

}

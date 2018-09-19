package com.iwona.trafficlights.config.startupinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iwona.trafficlights.app.dto.semaphore.SM11;
import com.iwona.trafficlights.app.dto.semaphore.SM12;
import com.iwona.trafficlights.app.dto.semaphore.SS21;
import com.iwona.trafficlights.app.dto.semaphore.SS22;
import com.iwona.trafficlights.app.manager.LightManager;
import com.iwona.trafficlights.app.scheduler.SemaphoreVisitor;

@Component
public class SemaphoreRegistry {

	private LightManager lightManager;
	private SemaphoreVisitor semaphoreVisitor;
	private SM11 sm11;
	private SM12 sm12;
	private SS21 ss21;
	private SS22 ss22;
	

	protected SemaphoreRegistry() {
		super();
	}

	@Autowired
	public SemaphoreRegistry(LightManager lightManager, SemaphoreVisitor semaphoreVisitor,SM11 sm11, SM12 sm12, SS21 ss21, SS22 ss22) {
		super();
		this.lightManager = lightManager;
		this.semaphoreVisitor = semaphoreVisitor;
		this.sm11 = sm11;
		this.sm12 = sm12;
		this.ss21 = ss21;
		this.ss22 = ss22;
	}

	public void registerSemaphores() {
		lightManager.addPropertyChangeListener(sm11);
		lightManager.addPropertyChangeListener(sm12);
		lightManager.addPropertyChangeListener(ss21);
		lightManager.addPropertyChangeListener(ss22);
	}
	
	public void registerSemaphoreVisitor() {
		semaphoreVisitor.addSemaphoreToWatch(sm11);
		semaphoreVisitor.addSemaphoreToWatch(sm12);
		semaphoreVisitor.addSemaphoreToWatch(ss21);
		semaphoreVisitor.addSemaphoreToWatch(ss22);
	}

}

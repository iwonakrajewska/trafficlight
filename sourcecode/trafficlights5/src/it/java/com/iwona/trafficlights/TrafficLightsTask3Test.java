package com.iwona.trafficlights;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.iwona.trafficlights.app.dto.configuration.CurrentProgramSetUp;
import com.iwona.trafficlights.app.dto.configuration.ProgramConfiguration;
import com.iwona.trafficlights.app.dto.semaphore.SM11;
import com.iwona.trafficlights.app.manager.LightManager;
import com.iwona.trafficlights.app.scheduler.SemaphoreVisitor;
import com.iwona.trafficlights.app.service.ProgramService;
import com.iwona.trafficlights.config.startupinit.SemaphoreRegistry;
import com.iwona.trafficlights.config.startupinit.StartUpInit;
import com.iwona.trafficlights.task3.ProgamServiceTask3;
import com.iwona.trafficlights.task3.SemaphoreRegistryTask3;

public class TrafficLightsTask3Test {

	private final long testDurationSeconds = 20L;

	Boolean continueWatchingSemaphores = Boolean.TRUE;

	private ProgramService programService;

	private StartUpInit startUpInit;
	private ProgramConfiguration programConfiguration;
	private CurrentProgramSetUp currentProgramSetUp;
	private LightManager lightManager;
	private SemaphoreRegistry semaphoreRegistry;
	private SemaphoreVisitor semaphoreVisitor;
	private SM11 sm11;

	@Before
	public void setUp() {
		programService = new ProgamServiceTask3(null);
		programConfiguration = new ProgramConfiguration();
		currentProgramSetUp = new CurrentProgramSetUp();
		lightManager = new LightManager(currentProgramSetUp);
		semaphoreVisitor = new SemaphoreVisitor();
		sm11 = new SM11();
		semaphoreRegistry = new SemaphoreRegistryTask3(lightManager, semaphoreVisitor, sm11);

		startUpInit = new StartUpInit(programService, programConfiguration, currentProgramSetUp, lightManager,
				semaphoreRegistry);
	}

	@Test
	public void testRunTrafficLoghts() {

		ExecutorService executorWatchSemaphores = Executors.newSingleThreadExecutor();
		executorWatchSemaphores.submit(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Separate thread to start Semaphores Watch: " + threadName);
			while (continueWatchingSemaphores) {
				semaphoreVisitor.watchSemaphoreStatus();
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e1) {
					;
				}
			}

		});

		ExecutorService executorStopTest = Executors.newSingleThreadExecutor();
		executorStopTest.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(testDurationSeconds);
			} catch (InterruptedException e1) {
				;
			}
			String threadName = Thread.currentThread().getName();
			System.out.println("Separate thread to switchOff TrafficLights: " + threadName);
			lightManager.setContinueNextIteration(false);
			System.out.println("Separate thread to switchOff SemaphoreWatch: " + threadName);
			continueWatchingSemaphores = Boolean.FALSE;
		});

		startUpInit.initializeConfiguration();

	}


}

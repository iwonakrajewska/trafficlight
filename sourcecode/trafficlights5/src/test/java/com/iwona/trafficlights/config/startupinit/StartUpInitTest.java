package com.iwona.trafficlights.config.startupinit;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iwona.trafficlights.app.dto.configuration.CurrentProgramSetUp;
import com.iwona.trafficlights.app.dto.configuration.ProgramConfiguration;
import com.iwona.trafficlights.app.manager.LightManager;
import com.iwona.trafficlights.app.service.ProgramService;

public class StartUpInitTest {

	@InjectMocks
	private StartUpInit startUpInit;
	@Mock
	private ProgramService programService;
	@Mock
	private ProgramConfiguration programConfiguration;
	@Mock
	private CurrentProgramSetUp currentProgramSetUp;
	@Mock
	private LightManager lightManager;
	@Mock
	private SemaphoreRegistry semaphoreRegistry;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testInitializeConfiguration() {
		startUpInit.initializeConfiguration();
		verify(programConfiguration).setProgramList(programService.loadPrograms());
		verify(programConfiguration).setProgramSchedule(programService.loadSchedule());
		verify(currentProgramSetUp).setNextProgram(programConfiguration.getProgramById(Integer.valueOf(1)));
		verify(currentProgramSetUp).setCurrentProgram(programConfiguration.getProgramById(Integer.valueOf(1)));
		verify(semaphoreRegistry).registerSemaphores();
		verify(lightManager).manageLight();
	}

}

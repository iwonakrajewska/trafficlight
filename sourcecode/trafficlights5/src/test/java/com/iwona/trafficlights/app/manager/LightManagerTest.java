package com.iwona.trafficlights.app.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iwona.trafficlights.app.dto.configuration.CurrentProgramSetUp;
import com.iwona.trafficlights.app.dto.semaphore.SM11;
import com.iwona.trafficlights.app.dto.semaphore.Semaphore;
import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.ProgramSteps;

public class LightManagerTest {

	@InjectMocks
	private LightManager lightManager;
	@Mock
	private  CurrentProgramSetUp currentProgramSetUp;
	@Mock
	private  PropertyChangeSupport support;
	private Semaphore semaphore = new SM11();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(currentProgramSetUp.getCurrentProgram()).thenReturn(createProgram());
	}

	@Test
	public void testSingleIteration() {
		lightManager.addPropertyChangeListener(semaphore);
		lightManager.applySingleIterationCycle();
		assertEquals("M11 : YEL",semaphore.getNameWithColour());
	}

	private Program createProgram() {
		Program program = new Program() ;
		program.setId(1);
		List<ProgramSteps> programSteps = new ArrayList<>();
		ProgramSteps step1 = new ProgramSteps();
		step1.setId(1);
		step1.setProgramId(1);
		step1.setSequence(1);
		step1.setStepDuration(2000L);
		programSteps.add(step1);
		Map<Integer, Integer> step1ConfigurationMap =new HashMap<>();
		step1ConfigurationMap.put(1, 3);
		step1.setStepConfigurationMap(step1ConfigurationMap);
		
		ProgramSteps step2 = new ProgramSteps();
		step2.setId(2);
		step2.setProgramId(2);
		step2.setSequence(2);
		step2.setStepDuration(2000L);
		programSteps.add(step2);
		Map<Integer, Integer> step2ConfigurationMap =new HashMap<>();
		step2ConfigurationMap.put(1, 2);
		step2.setStepConfigurationMap(step2ConfigurationMap);

		program.setProgramSteps(programSteps);
		return program;
	}
}

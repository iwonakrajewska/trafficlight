package com.iwona.trafficlights.app.dto.configuration;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.iwona.trafficlights.app.entity.program.Program;

public class CurrentProgramSetUpTest {

	@InjectMocks
	CurrentProgramSetUp currentProgramSetUp;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testNextMethod() {
		currentProgramSetUp.setNextProgram(new Program());
		assertNotNull(currentProgramSetUp.getNextProgram());
	}

	@Test
	public void testcurrentStepConfigurationMap() {
		currentProgramSetUp.setCurrentStepConfigurationMap(new HashMap<>());
		assertNotNull(currentProgramSetUp.getCurrentStepConfigurationMap());
	}

	@Test
	public void testCurrentProgram() {
		currentProgramSetUp.setCurrentProgram(new Program());
		assertNotNull(currentProgramSetUp.getCurrentProgram());
	}

}

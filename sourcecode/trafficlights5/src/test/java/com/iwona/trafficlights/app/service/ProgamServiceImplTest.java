package com.iwona.trafficlights.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iwona.trafficlights.app.dao.program.ProgramDao;
import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.ProgramSteps;
import com.iwona.trafficlights.app.entity.program.StepConfiguration;

public class ProgamServiceImplTest {

	@InjectMocks
	private ProgamServiceImpl progamServiceImpl;
	@Mock
	private ProgramDao programDao;
	private List<Program> programList ;

	@Before
	public void setUp() {
		 programList = createProgramList();
		MockitoAnnotations.initMocks(this);
		when(programDao.loadAllPrograms()).thenReturn(programList);

	}

	@Test
	public void testLoadSchedule() {
		progamServiceImpl.loadSchedule();
		verify(programDao).loadProgramSchedule();
	}

	@Test
	public void testLoadPrograms() {
		progamServiceImpl.loadPrograms();
		verify(programDao).loadAllPrograms();
		assertNotNull(programList.get(0).getProgramSteps().get(0).getStepConfigurationMap());
		assertEquals(Integer.valueOf(7),programList.get(0).getProgramSteps().get(0).getStepConfigurationMap().get(6));
	}

	private List<Program> createProgramList() {
		List<Program> programList = new ArrayList<>();
		Program program = new Program();
		List<ProgramSteps> programSteps = new ArrayList<>();
		ProgramSteps programStep = new ProgramSteps();
		List<StepConfiguration> stepConfigurationList = new ArrayList<>();
		StepConfiguration stepConfiguration = new StepConfiguration();
		stepConfiguration.setSemaphoreTypeId(6);
		stepConfiguration.setColourId(7);
		stepConfigurationList.add(stepConfiguration );
		programStep.setStepConfiguration(stepConfigurationList );
		programSteps.add(programStep );
		program.setProgramSteps(programSteps );
		programList.add(program);
		return programList;
	}

}

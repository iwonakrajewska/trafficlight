package com.iwona.trafficlights.app.dto.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.Schedule;

public class ProgramConfigurationTest {

	@InjectMocks
	private ProgramConfiguration programConfiguration;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testEmptygetProgramById() {
		assertNull(programConfiguration.getProgramById(null));
		assertNull(programConfiguration.getProgramById(Integer.valueOf(99)));
	}

	@Test
	public void testGetProgramById() {
		List<Program> programList = new ArrayList<>();
		Program p = new Program();
		p.setId(Integer.valueOf(5));
		programList.add(p);
		programConfiguration.setProgramList(programList);

		assertEquals(p, programConfiguration.getProgramById(Integer.valueOf(5)));
	}

	@Test
	public void testScheduleList() {
		programConfiguration.setProgramSchedule(new ArrayList<Schedule>());
		assertNotNull(programConfiguration.getProgramSchedule());
	}
}

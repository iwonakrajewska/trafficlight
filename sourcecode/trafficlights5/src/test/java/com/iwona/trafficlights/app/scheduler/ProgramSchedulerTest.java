package com.iwona.trafficlights.app.scheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;

import com.iwona.trafficlights.app.dto.configuration.CurrentProgramSetUp;
import com.iwona.trafficlights.app.dto.configuration.ProgramConfiguration;
import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.Schedule;

public class ProgramSchedulerTest {

	@InjectMocks
	private ProgramScheduler programScheduler;
	@Mock
	private ProgramConfiguration programConfiguration;
	@Mock
	private CurrentProgramSetUp currentProgramSetUp;
	@Mock
	private Program program;
	@Mock
	private Program program5;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(programConfiguration.getProgramById(1)).thenReturn(program);
		when(programConfiguration.getProgramById(5)).thenReturn(program5);
	}

	@Test
	public void testEmptySchedule() {
		programScheduler.checkCurrentProgram();
		verify(currentProgramSetUp).setNextProgram(program);
	}

	@Test
	public void testSchedule() {
		when(programConfiguration.getProgramSchedule()).thenReturn(createScheduleList());
		programScheduler.checkCurrentProgram();
		verify(currentProgramSetUp, times(0)).setNextProgram(program);
		verify(currentProgramSetUp, times(1)).setNextProgram(program5);
	}

	private List<Schedule> createScheduleList() {
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalTime localTime = localDateTime.toLocalTime();
		LocalTime start = localTime.minusMinutes(1);
		LocalTime end = localTime.plusMinutes(5);
		DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();

		List<Schedule> scheduleList = new ArrayList<>();
		Schedule schedule = new Schedule();
		schedule.setId(22);
		schedule.setProgramId(5);
		schedule.setWeekDayNumber(dayOfWeek.getValue());
		schedule.setStartTime(Time.valueOf(start));
		schedule.setEndTime(Time.valueOf(end));

		scheduleList.add(schedule);
		return scheduleList;
	}

}

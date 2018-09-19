package com.iwona.trafficlights.app.scheduler;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.iwona.trafficlights.app.dto.configuration.CurrentProgramSetUp;
import com.iwona.trafficlights.app.dto.configuration.ProgramConfiguration;
import com.iwona.trafficlights.app.entity.program.Schedule;

@Configuration
@EnableScheduling
public class ProgramScheduler {

	private static final Logger LOGGER = LogManager.getLogger(ProgramScheduler.class);

	private final CurrentProgramSetUp currentProgramSetUp;
	private final ProgramConfiguration programConfiguration;

	@Autowired
	public ProgramScheduler(CurrentProgramSetUp currentProgramSetUp, ProgramConfiguration programConfiguration) {
		super();
		this.currentProgramSetUp = currentProgramSetUp;
		this.programConfiguration = programConfiguration;
	}

	@Scheduled(cron = "${scheduler.check.current.program.cron}")
	public void checkCurrentProgram() {

		LocalDateTime localDateTime = LocalDateTime.now();
		LocalTime localTime = localDateTime.toLocalTime();
		DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();

		List<Schedule> scheduleList = programConfiguration.getProgramSchedule();
		Integer programId = retrieveSchedule(localTime, dayOfWeek, scheduleList);
		LOGGER.info("Setting nextProgramId: " + programId + ", for currentDayOfWeek: " + dayOfWeek.getValue()
				+ ", CurentTime: " + localTime);
		currentProgramSetUp.setNextProgram(programConfiguration.getProgramById(programId));
	}

	private Integer retrieveSchedule(LocalTime localTime, DayOfWeek dayOfWeek, List<Schedule> scheduleList) {
		for (Schedule schedule : scheduleList) {
			if (schedule.getWeekDayNumber().equals(dayOfWeek.getValue())) {
				LocalTime start = schedule.getStartTime().toLocalTime();
				LocalTime end = schedule.getEndTime().toLocalTime();
				if ((!localTime.isBefore(start)) && (localTime.isBefore(end))) {
					return schedule.getProgramId();
				}
			}
		}
		return Integer.valueOf(1);
	}

}

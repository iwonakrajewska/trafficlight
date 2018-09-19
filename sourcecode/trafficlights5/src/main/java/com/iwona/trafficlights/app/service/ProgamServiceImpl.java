package com.iwona.trafficlights.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iwona.trafficlights.app.dao.program.ProgramDao;
import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.ProgramSteps;
import com.iwona.trafficlights.app.entity.program.Schedule;
import com.iwona.trafficlights.app.entity.program.StepConfiguration;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProgamServiceImpl implements ProgramService {

	private static final Logger LOGGER = LogManager.getLogger(ProgamServiceImpl.class);

	private final ProgramDao programDao;

	@Autowired
	public ProgamServiceImpl(ProgramDao programDao) {
		this.programDao = programDao;
	}

	@Override
	public List<Program> loadPrograms() {
		List<Program> programList = this.programDao.loadAllPrograms();
		LOGGER.info("ProgramList loaded" + programList.toString());
		buildStepsConfigurationMap(programList);
		return programList;
	}

	@Override
	public List<Schedule> loadSchedule() {
		List<Schedule> scheduleList = this.programDao.loadProgramSchedule();
		LOGGER.info("ScheduleList loaded" + scheduleList.toString());
		return scheduleList;
	}

	protected void buildStepsConfigurationMap(List<Program> programList) {
		for (Program program : programList) {
			for (ProgramSteps step : program.getProgramSteps()) {
				Map<Integer, Integer> stepConfigurationMap = new HashMap<>();
				step.setStepConfigurationMap(stepConfigurationMap);
				for (StepConfiguration stepConfiguration : step.getStepConfiguration()) {
					stepConfigurationMap.put(stepConfiguration.getSemaphoreTypeId(), stepConfiguration.getColourId());
				}
			}
		}
	}

}
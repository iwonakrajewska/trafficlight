package com.iwona.trafficlights.app.dto.configuration;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.Schedule;

@Component
public class ProgramConfiguration {

	private List<Program> programList;

	private List<Schedule> programSchedule;

	public Program getProgramById(Integer programId) {
		if (programId == null || CollectionUtils.isEmpty(programList))
			return null;

		for (Program program : programList) {
			if (program.getId().equals(programId)) {
				return program;
			}
		}
		return null;
	}

	public List<Program> getProgramList() {
		return programList;
	}

	public void setProgramList(List<Program> programList) {
		this.programList = programList;
	}

	public List<Schedule> getProgramSchedule() {
		return programSchedule;
	}

	public void setProgramSchedule(List<Schedule> programSchedule) {
		this.programSchedule = programSchedule;
	}

}

package com.iwona.trafficlights.app.dao.program;

import java.util.List;

import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.Schedule;

public interface ProgramDao {

	List<Program> loadAllPrograms();

	List<Schedule> loadProgramSchedule();

}

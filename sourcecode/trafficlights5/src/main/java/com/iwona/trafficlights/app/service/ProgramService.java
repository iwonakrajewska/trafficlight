package com.iwona.trafficlights.app.service;

import java.util.List;

import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.Schedule;

public interface ProgramService {

	List<Program> loadPrograms();

	List<Schedule> loadSchedule();

}

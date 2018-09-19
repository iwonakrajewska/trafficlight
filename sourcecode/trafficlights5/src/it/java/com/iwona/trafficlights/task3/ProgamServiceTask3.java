package com.iwona.trafficlights.task3
;

import java.util.ArrayList;
import java.util.List;

import com.iwona.trafficlights.app.dao.program.ProgramDao;
import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.ProgramSteps;
import com.iwona.trafficlights.app.entity.program.Schedule;
import com.iwona.trafficlights.app.entity.program.StepConfiguration;
import com.iwona.trafficlights.app.service.ProgamServiceImpl;

public class ProgamServiceTask3 extends ProgamServiceImpl {

	public ProgamServiceTask3(ProgramDao programDao) {
		super(programDao);
	}

	@Override
	public List<Schedule> loadSchedule() {
		List<Schedule> scheduleList = new ArrayList<>();
		return scheduleList;
	}

	@Override
	public List<Program> loadPrograms() {
		List<Program> programList = createProgramList();
		buildStepsConfigurationMap(programList);
		return programList;
	}

	private List<Program> createProgramList() {
		ProgramSteps step1 = new ProgramSteps();
		step1.setId(1);
		step1.setProgramId(1);
		step1.setSequence(1);
		step1.setStepDuration(2000L);
		List<StepConfiguration> stepConfigurationList1 = new ArrayList<>();
		StepConfiguration stepConfig1 = new StepConfiguration();
		stepConfig1.setId(1);
		stepConfig1.setStepId(1);
		stepConfig1.setSemaphoreTypeId(1);
		stepConfig1.setColourId(3);
		stepConfigurationList1.add(stepConfig1);
		step1.setStepConfiguration(stepConfigurationList1);

		ProgramSteps step2 = new ProgramSteps();
		step2.setId(2);
		step2.setProgramId(1);
		step2.setSequence(2);
		step2.setStepDuration(2000L);
		List<StepConfiguration> stepConfigurationList2 = new ArrayList<>();

		StepConfiguration stepConfig2 = new StepConfiguration();
		stepConfig2.setId(2);
		stepConfig2.setStepId(1);
		stepConfig2.setSemaphoreTypeId(1);
		stepConfig2.setColourId(1);
		stepConfigurationList2.add(stepConfig2);
		step2.setStepConfiguration(stepConfigurationList2);

		ProgramSteps step3 = new ProgramSteps();
		step3.setId(3);
		step3.setProgramId(1);
		step3.setSequence(3);
		step3.setStepDuration(2000L);
		List<StepConfiguration> stepConfigurationList3 = new ArrayList<>();

		StepConfiguration stepConfig3 = new StepConfiguration();
		stepConfig3.setId(3);
		stepConfig3.setStepId(1);
		stepConfig3.setSemaphoreTypeId(1);
		stepConfig3.setColourId(2);
		stepConfigurationList3.add(stepConfig3);
		step3.setStepConfiguration(stepConfigurationList3);

		List<ProgramSteps> programSteps = new ArrayList<>();
		programSteps.add(step1);
		programSteps.add(step2);
		programSteps.add(step3);
		Program program = new Program();
		program.setId(1);
		program.setTitle("TestProgram");
		program.setProgramSteps(programSteps);

		List<Program> programList = new ArrayList<>();
		programList.add(program);
		return programList;
	}

}

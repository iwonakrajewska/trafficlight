package com.iwona.trafficlights.app.dto.configuration;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.iwona.trafficlights.app.entity.program.Program;

@Component
public class CurrentProgramSetUp {

	private Program nextProgram;
	private Program currentProgram;
	private Map<Integer, Integer> currentStepConfigurationMap = null;

	public Program getNextProgram() {
		return nextProgram;
	}

	public void setNextProgram(Program nextProgram) {
		this.nextProgram = nextProgram;
	}

	public Program getCurrentProgram() {
		return currentProgram;
	}

	public void setCurrentProgram(Program currentProgram) {
		this.currentProgram = currentProgram;
	}

	public Map<Integer, Integer> getCurrentStepConfigurationMap() {
		return currentStepConfigurationMap;
	}

	public void setCurrentStepConfigurationMap(Map<Integer, Integer> currentStepConfigurationMap) {
		this.currentStepConfigurationMap = currentStepConfigurationMap;
	}

}

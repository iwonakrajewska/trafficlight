package com.iwona.trafficlights.app.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import com.iwona.trafficlights.app.dto.configuration.CurrentProgramSetUp;
import com.iwona.trafficlights.app.entity.program.Program;
import com.iwona.trafficlights.app.entity.program.ProgramSteps;

@Configuration
@EnableAsync
public class LightManager {

	private static final Logger LOGGER = LogManager.getLogger(LightManager.class);

	private final CurrentProgramSetUp currentProgramSetUp;
	private final PropertyChangeSupport support;
	
	private boolean continueNextIteration = true;

	@Autowired
	public LightManager(CurrentProgramSetUp currentProgramSetUp) {
		support = new PropertyChangeSupport(this);
		this.currentProgramSetUp = currentProgramSetUp;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

	@Async
	public void manageLight() {
		while (continueNextIteration) {
			applySingleIterationCycle();
		}
	}

	void applySingleIterationCycle() {
		currentProgramSetUp.setCurrentProgram(currentProgramSetUp.getNextProgram());

		Program currentProgram = currentProgramSetUp.getCurrentProgram();
		List<ProgramSteps> steps = currentProgram.getProgramSteps();

		for (ProgramSteps step : steps) {
			Map<Integer, Integer> newStepConfigurationMap = step.getStepConfigurationMap();
			LOGGER.info("Pushing configuration for programId:" + step.getProgramId() + ", stepid: " + step.getId()
					+ ", stepSequence:" + step.getSequence() + ", stepDuration:" + step.getStepDuration());
			support.firePropertyChange("stepConfigurationMap", currentProgramSetUp.getCurrentStepConfigurationMap(),
					newStepConfigurationMap);
			currentProgramSetUp.setCurrentStepConfigurationMap(newStepConfigurationMap);
			try {
				TimeUnit.MILLISECONDS.sleep(step.getStepDuration());
			} catch (InterruptedException e1) {
				;
			}
		}
	}

	public void setContinueNextIteration(boolean continueNextIteration) {
		this.continueNextIteration = continueNextIteration;
	}
	
	

}

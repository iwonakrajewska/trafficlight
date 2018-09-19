package com.iwona.trafficlights.app.entity.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table(name = "PROGRAM_STEPS", schema = "TRAFFICLIGHTS")
public class ProgramSteps {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "PROGRAM_ID")
	private Integer programId;
	@Column(name = "STEP_SEQUENCE")
	private Integer sequence;
	@Column(name = "STEP_DURATION")
	private Long stepDuration;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "STEP_ID")
	private List<StepConfiguration> stepConfiguration = new ArrayList<>();
	@Transient
	private Map<Integer, Integer> stepConfigurationMap;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProgramId() {
		return programId;
	}

	public void setProgramId(Integer programId) {
		this.programId = programId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Long getStepDuration() {
		return stepDuration;
	}

	public void setStepDuration(Long stepDuration) {
		this.stepDuration = stepDuration;
	}

	public List<StepConfiguration> getStepConfiguration() {
		return stepConfiguration;
	}

	public void setStepConfiguration(List<StepConfiguration> stepConfiguration) {
		this.stepConfiguration = stepConfiguration;
	}

	public Map<Integer, Integer> getStepConfigurationMap() {
		return stepConfigurationMap;
	}

	public void setStepConfigurationMap(Map<Integer, Integer> stepConfigurationMap) {
		this.stepConfigurationMap = stepConfigurationMap;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}

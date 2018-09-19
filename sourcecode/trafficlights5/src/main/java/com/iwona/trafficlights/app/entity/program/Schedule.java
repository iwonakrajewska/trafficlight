package com.iwona.trafficlights.app.entity.program;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table(name = "PROGRAM_SCHEDULE", schema = "TRAFFICLIGHTS")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "PROGRAM_ID")
	private Integer programId;
	@Column(name = "PRIORITY")
	private Integer priority;
	@Column(name = "WEEK_DAY_NUMBER")
	private Integer weekDayNumber;
	@Column(name = "START_TIME")
	private Time startTime;
	@Column(name = "END_TIME")
	private Time endTime;

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

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getWeekDayNumber() {
		return weekDayNumber;
	}

	public void setWeekDayNumber(Integer weekDayNumber) {
		this.weekDayNumber = weekDayNumber;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}

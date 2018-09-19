package com.iwona.trafficlights.app.entity.program;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table(name = "PROGRAM", schema = "TRAFFICLIGHTS")
public class Program {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "TITLE")
	private String title;
	@Column(name = "DESCRIPTION")
	private String description;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROGRAM_ID")
	@OrderBy("STEP_SEQUENCE")
	private List<ProgramSteps> programSteps = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProgramSteps> getProgramSteps() {
		return programSteps;
	}

	public void setProgramSteps(List<ProgramSteps> programSteps) {
		this.programSteps = programSteps;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}

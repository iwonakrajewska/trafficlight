package com.iwona.trafficlights.app.dto.colour;

public enum LightColour {
	
	RED(Integer.valueOf(3), "RED"), 
	YELLOW(Integer.valueOf(2), "YEL"), 
	GREEN(Integer.valueOf(1), "GRE"),
	BLACK(Integer.valueOf(0), "BLA");

	private Integer colourId;
	private String shortName;

	LightColour(Integer colourId, String shortName) {
		this.colourId = colourId;
		this.shortName = shortName;
	}

	public Integer getColourId() {
		return colourId;
	}

	public String getShortName() {
		return shortName;
	}

}

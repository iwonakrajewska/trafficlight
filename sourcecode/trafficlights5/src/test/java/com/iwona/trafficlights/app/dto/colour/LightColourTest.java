package com.iwona.trafficlights.app.dto.colour;

import org.junit.Assert;
import org.junit.Test;

public class LightColourTest {

	@Test
	public void testGetColourId() {
		Assert.assertEquals(Integer.valueOf(0), LightColour.BLACK.getColourId());
	}

	@Test
	public void testGetShortName() {
		Assert.assertEquals("BLA", LightColour.BLACK.getShortName());
	}

}

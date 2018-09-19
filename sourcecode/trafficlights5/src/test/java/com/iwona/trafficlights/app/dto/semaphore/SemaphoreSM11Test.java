package com.iwona.trafficlights.app.dto.semaphore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class SemaphoreSM11Test {

	@InjectMocks
	private SM11 semaphore;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testInitialValues() {
		assertFalse(semaphore.isGreen());
		assertFalse(semaphore.isYellow());
		assertFalse(semaphore.isRed());
		assertEquals("M11", semaphore.getSemaphoreName());
		assertEquals(SemaphoreType.MAIN_STREET, semaphore.getSemaphoreType());
		assertEquals("M11 : BLA", semaphore.getNameWithColour());
	}

	@Test
	public void testYelPush() {
		Map<Integer, Integer> newStepConfigurationMap = new HashMap<>();
		newStepConfigurationMap.put(1, 2);
		PropertyChangeEvent event = new PropertyChangeEvent(semaphore, null, null, newStepConfigurationMap);
		semaphore.propertyChange(event);
		assertEquals("M11 : YEL", semaphore.getNameWithColour());
	}

	@Test
	public void testRedPush() {
		Map<Integer, Integer> newStepConfigurationMap = new HashMap<>();
		newStepConfigurationMap.put(1, 3);
		PropertyChangeEvent event = new PropertyChangeEvent(semaphore, null, null, newStepConfigurationMap);
		semaphore.propertyChange(event);
		assertEquals("M11 : RED", semaphore.getNameWithColour());
	}

	@Test
	public void testGreenPush() {
		Map<Integer, Integer> newStepConfigurationMap = new HashMap<>();
		newStepConfigurationMap.put(1, 1);
		PropertyChangeEvent event = new PropertyChangeEvent(semaphore, null, null, newStepConfigurationMap);
		semaphore.propertyChange(event);
		assertEquals("M11 : GRE", semaphore.getNameWithColour());
	}

	@Test
	public void testNothingPush() {
		Map<Integer, Integer> newStepConfigurationMap = new HashMap<>();
		PropertyChangeEvent event = new PropertyChangeEvent(semaphore, null, null, newStepConfigurationMap);
		semaphore.propertyChange(event);
		assertEquals("M11 : BLA", semaphore.getNameWithColour());
	}

}

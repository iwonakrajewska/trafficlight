package com.iwona.trafficlights.config.startupinit;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iwona.trafficlights.app.dto.semaphore.SM11;
import com.iwona.trafficlights.app.dto.semaphore.SM12;
import com.iwona.trafficlights.app.dto.semaphore.SS21;
import com.iwona.trafficlights.app.dto.semaphore.SS22;
import com.iwona.trafficlights.app.manager.LightManager;

public class SemaphoreRegistryTest {

	@InjectMocks
	private SemaphoreRegistry semaphoreRegistry;
	@Mock
	private LightManager lightManager;
	@Mock
	private SM11 sm11;
	@Mock
	private SM12 sm12;
	@Mock
	private SS21 ss21;
	@Mock
	private SS22 ss22;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSemaphoreRegistry() {
		semaphoreRegistry.registerSemaphores();
		verify(lightManager).addPropertyChangeListener(sm11);
		verify(lightManager).addPropertyChangeListener(sm12);
		verify(lightManager).addPropertyChangeListener(ss21);
		verify(lightManager).addPropertyChangeListener(ss22);
	}

}

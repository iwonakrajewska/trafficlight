package com.iwona.trafficlights.app.scheduler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.iwona.trafficlights.app.dto.semaphore.SM11;
import com.iwona.trafficlights.app.dto.semaphore.SM12;
import com.iwona.trafficlights.app.dto.semaphore.SS21;
import com.iwona.trafficlights.app.dto.semaphore.SS22;

public class SemaphoreVisitorTest {

	private SemaphoreVisitor semaphoreVisitor;
	private final SM11 sm11 = new SM11();
	private final SM12 sm12 = new SM12();
	private final SS21 ss21 = new SS21();
	private final SS22 ss22 = new SS22();

	@Before
	public void setUp() {
		semaphoreVisitor = new SemaphoreVisitor();
		semaphoreVisitor.addSemaphoreToWatch(sm11);
		semaphoreVisitor.addSemaphoreToWatch(sm12);
		semaphoreVisitor.addSemaphoreToWatch(ss21);
		semaphoreVisitor.addSemaphoreToWatch(ss22);
	}

	@Test
	public void testVisit() {
		assertEquals("M11 : BLA", semaphoreVisitor.visit(sm11));
		assertEquals("M12 : BLA", semaphoreVisitor.visit(sm12));
		assertEquals("S21 : BLA", semaphoreVisitor.visit(ss21));
		assertEquals("S22 : BLA", semaphoreVisitor.visit(ss22));
	}
	

	@Test
	public void testWatchSemaphoreStatus() {
		assertEquals("M11 : BLA	M12 : BLA	S21 : BLA	S22 : BLA", semaphoreVisitor.watchSemaphoreStatus());
	}

}
package com.iwona.trafficlights.app.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.iwona.trafficlights.app.dto.semaphore.SM11;
import com.iwona.trafficlights.app.dto.semaphore.SM12;
import com.iwona.trafficlights.app.dto.semaphore.SS21;
import com.iwona.trafficlights.app.dto.semaphore.SS22;
import com.iwona.trafficlights.app.dto.semaphore.Semaphore;

@Configuration
@EnableScheduling
public class SemaphoreVisitor implements SemaphoreWatch {

	private static final Logger LOGGER = LogManager.getLogger(SemaphoreVisitor.class);

	private final SM11 sm11;
	private final SM12 sm12;
	private final SS21 ss21;
	private final SS22 ss22;


	public SemaphoreVisitor(SM11 sm11,
			SM12 sm12, SS21 ss21, SS22 ss22) {
		super();
		this.sm11 = sm11;
		this.sm12 = sm12;
		this.ss21 = ss21;
		this.ss22 = ss22;
	}

	@Scheduled(cron = "${scheduler.semaphore.visitor.cron}")
	public void watchSemaphoreStatus() {
		LOGGER.info(
				"Semaphores watch:\t" + visit(sm11) + "\t" + visit(sm12) + "\t" + visit(ss21) + "\t"
						+ visit(ss22));
	}

	@Override
	public String visit(Semaphore semaphore) {
		return semaphore.getNameWithColour();
	}

}

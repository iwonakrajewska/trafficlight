package com.iwona.trafficlights.app.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.iwona.trafficlights.app.dto.semaphore.Semaphore;

@Configuration
@EnableScheduling
public class SemaphoreVisitor implements SemaphoreWatch {

	private static final Logger LOGGER = LogManager.getLogger(SemaphoreVisitor.class);

	private final List<Semaphore> semaphoreToWatchList;

	public SemaphoreVisitor() {
		semaphoreToWatchList = new ArrayList<>();
	}

	public void addSemaphoreToWatch(Semaphore semaphore) {
		semaphoreToWatchList.add(semaphore);
	}

	@Scheduled(cron = "${scheduler.semaphore.visitor.cron}")
	public String watchSemaphoreStatus() {
		String semaphoreOutput = semaphoreToWatchList.stream()
				.map(semaphore -> visit(semaphore))
				.collect(Collectors.joining("\t"));

		LOGGER.info("Semaphores watch:\t" + semaphoreOutput);
		return semaphoreOutput;
	}

	@Override
	public String visit(Semaphore semaphore) {
		return semaphore.getNameWithColour();
	}

}

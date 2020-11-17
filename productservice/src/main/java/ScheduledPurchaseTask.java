import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableAsync
@EnableScheduling
public class ScheduledPurchaseTask {
	private static final Logger log = LoggerFactory.getLogger(ScheduledPurchaseTask.class);

	/**
	 * Scheduled execution for the random product purchase scenario
	 * @throws URISyntaxException
	 */
	// 30 seconds delay
	@Scheduled(fixedDelay = 30000)
	public void scheduledPurchase() throws URISyntaxException {
		log.info("Starting the product purchase : ");
	}
}

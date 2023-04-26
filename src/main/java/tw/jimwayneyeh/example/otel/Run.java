package tw.jimwayneyeh.example.otel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class Run implements CommandLineRunner {
    private SecureRandom random = new SecureRandom();
    @Autowired
    private FakeEventProcessor processor1;
    @Autowired
    private FakeEventProcessor processor2;

    @Override
    public void run(String... args) throws Exception {
        log.info("Run");

        produceEvents(1_000_000, 10);

        log.info("Done");
    }

    private void produceEvents(int numOfEvents, int numOfThreads) {
        var executor = Executors.newFixedThreadPool(numOfThreads);

        for (int i = 0; i < numOfEvents; i++) {
            executor.submit(() -> {
                if (random.nextBoolean()) {
                    processor1.receiveEvent(generateRandomEvent());
                } else {
                    processor2.receiveEvent(generateRandomEvent());
                }
            });
        }
    }

    private Event generateRandomEvent() {
        var eventType = "";
        switch (random.nextInt(3)) {
            case 1:
                eventType = "update";
                break;
            case 2:
                eventType = "delete";
                break;
            default:
                eventType = "create";
        }

        return Event.builder()
                .eventType(eventType)
                .owner("owner-" + random.nextInt(2))
                .build();
    }
}

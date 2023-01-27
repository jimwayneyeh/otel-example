package tw.jimwayneyeh.example.otel;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Slf4j
@Component
public class Run implements CommandLineRunner {
    private SecureRandom random = new SecureRandom();
    @Override
    public void run(String... args) throws Exception {
        log.info("Run");

        int loop = 100;
        while(loop-- > 0) {
            var event = generateRandomEvent();
            receiveEvent(event);
        }
    }

    private Event generateRandomEvent() {
        var eventType = "";
        switch(random.nextInt(3)) {
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
                .owner("owner-" + random.nextInt(10))
                .build();
    }

    @Builder
    class Event {
        private String eventType;
        private String owner;
    }

    private void receiveEvent(Event event) {
        // Do nothing.
    }
}

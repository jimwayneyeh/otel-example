package tw.jimwayneyeh.example.otel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Slf4j
@Component
public class Run implements CommandLineRunner {
    private SecureRandom random = new SecureRandom();
    @Autowired
    private FakeEventProcessor processor;
    @Override
    public void run(String... args) throws Exception {
        log.info("Run");

        int loop = 10;
        while(loop-- > 0) {
            var event = generateRandomEvent();
            processor.receiveEvent(event);
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
}

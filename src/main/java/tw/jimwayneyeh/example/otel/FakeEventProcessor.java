package tw.jimwayneyeh.example.otel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FakeEventProcessor {
    public void receiveEvent(Event event) {
        // log.info("Receive: {}", event);
    }
}

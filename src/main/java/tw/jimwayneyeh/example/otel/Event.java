package tw.jimwayneyeh.example.otel;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Builder
@Getter
@Accessors(fluent = true)
@ToString
public class Event {
    private String eventType;
    private String owner;
}

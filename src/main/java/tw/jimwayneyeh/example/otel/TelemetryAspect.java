package tw.jimwayneyeh.example.otel;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TelemetryAspect {

    private OpenTelemetry telemetry;

    private Meter meter;
    private LongCounter eventCounter;

    @Autowired
    public TelemetryAspect(OpenTelemetry telemetry) {
        log.trace("Initiate aspect...");
        this.telemetry = telemetry;
        initiateMeter();
    }

    private void initiateMeter() {
        meter = telemetry.meterBuilder("event-consumer")
                .setInstrumentationVersion("1.0.0")
                .build();

        eventCounter = meter.counterBuilder("eventType")
                .setDescription("Metrics for the event consuming.")
                .setUnit("1")
                .build();
    }

    @Before("execution(* tw.jimwayneyeh.example.otel.FakeEventProcessor.receiveEvent(..))")
    public void before(JoinPoint joinPoint) {
        var event = (Event) joinPoint.getArgs()[0];
        eventCounter.add(1, Attributes.of(
                AttributeKey.stringKey("eventType"), event.eventType(),
                AttributeKey.stringKey("owner"), event.owner()));
    }
}

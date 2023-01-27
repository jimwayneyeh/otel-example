package tw.jimwayneyeh.example.otel;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TelemetryAspect {

    public TelemetryAspect() {
        log.info("Initiate aspect...");
    }

    @Before("execution(* tw.jimwayneyeh.example.otel.FakeEventProcessor.receiveEvent(..))")
    public void before(JoinPoint joinPoint) {
        Event event = (Event) joinPoint.getArgs()[0];
        log.trace("join '{}()': {}", joinPoint.getSignature().getName(), event);
    }
}

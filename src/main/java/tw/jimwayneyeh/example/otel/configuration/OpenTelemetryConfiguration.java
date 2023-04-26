package tw.jimwayneyeh.example.otel.configuration;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.exporter.logging.LoggingMetricExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.data.AggregationTemporality;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OpenTelemetryConfiguration {

    @Bean(destroyMethod = "")
    public OpenTelemetry getTelemetry() {
        var resource = Resource.getDefault()
                .merge(Resource.create(
                        Attributes.of(ResourceAttributes.SERVICE_NAME, "otel-example")));

        var sdkMeterProvider = SdkMeterProvider.builder()
                .registerMetricReader(
                        PeriodicMetricReader.builder(
                                        LoggingMetricExporter.create(AggregationTemporality.DELTA))
                                .setInterval(Duration.ofSeconds(1))
                                .build())
                .setResource(resource)
                .build();

        var openTelemetry = OpenTelemetrySdk.builder()
                .setMeterProvider(sdkMeterProvider)
                .buildAndRegisterGlobal();

        return openTelemetry;
    }
}

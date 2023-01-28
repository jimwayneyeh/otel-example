package tw.jimwayneyeh.example.otel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "tw.jimwayneyeh.example.otel")
public class OtelApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtelApplication.class, args);
	}

}

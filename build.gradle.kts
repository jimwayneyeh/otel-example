plugins {
	java
	id("org.springframework.boot") version "2.7.8"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "tw.jimwayneyeh.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// BOMs
	implementation(platform("io.opentelemetry:opentelemetry-bom:1.22.0"))
	implementation(platform("io.opentelemetry:opentelemetry-bom-alpha:1.22.0-alpha"))

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.slf4j:slf4j-api:1.7.36")
	implementation("io.opentelemetry:opentelemetry-api")
	implementation("io.opentelemetry:opentelemetry-sdk")
	implementation("io.opentelemetry:opentelemetry-semconv")
	implementation("io.opentelemetry:opentelemetry-exporter-logging")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

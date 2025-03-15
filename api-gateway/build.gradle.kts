plugins {
    id("java")
}

group = "org.kaistinea"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:4.1.5")
    implementation("org.springframework.boot:spring-boot-starter-security:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-webflux:3.2.2")


}

tasks.test {
    useJUnitPlatform()
}
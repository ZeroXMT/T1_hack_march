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
    implementation("org.springframework.boot:spring-boot-starter-security:3.4.2")
    implementation("org.telegram:telegrambots-spring-boot-starter:6.8.0")


}

tasks.test {
    useJUnitPlatform()
}
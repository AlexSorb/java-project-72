plugins {
    java
    application
    checkstyle
    jacoco
    id("io.freefair.lombok") version "8.12.2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}


group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("hexlet.code.App")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:6.4.0")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        csv.required = false
    }
}
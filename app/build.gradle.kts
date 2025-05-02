plugins {
    application
    checkstyle
    jacoco
    id("io.freefair.lombok") version "8.13"
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
    implementation("io.javalin:javalin:6.5.0")
    implementation("io.javalin:javalin-bundle:6.5.0")
    implementation("io.javalin:javalin-rendering:6.5.0")

    implementation("org.postgresql:postgresql:42.7.2")

    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("com.zaxxer:HikariCP:6.2.1")
    implementation("com.h2database:h2:2.3.232")
    implementation("gg.jte:jte:3.2.0")


    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("org.mockito:mockito-core:5.17.0")
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
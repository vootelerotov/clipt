plugins {
    application
    kotlin("jvm") version "1.8.0"
}

group = "io.github.vootelerotov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:3.5.2")

    implementation("com.theokanning.openai-gpt3-java:service:0.12.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("io.github.vootelerotov.clipt.CliptKt")
    applicationName = "clipt"
}
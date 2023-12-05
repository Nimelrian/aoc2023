plugins {
    kotlin("jvm")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.assertj:assertj-core:3.24.2")
}

application {
    mainClass.set("de.nimelrian.aoc2022.Day5Kt")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

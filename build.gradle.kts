plugins {
    id("java")
    id("org.springframework.boot") version "3.4.3"
}

apply(plugin = "io.spring.dependency-management")

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

sourceSets {
    val functionalTest by creating {
        compileClasspath += sourceSets["main"].output
        runtimeClasspath += sourceSets["main"].output
    }
}

val functionalTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations["implementation"])
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.hibernate.validator:hibernate-validator")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    functionalTestImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.register<Test>("functionalTest") {
    description = "Runs functional tests."
    group = "verification"
    testClassesDirs = sourceSets["functionalTest"].output.classesDirs
    classpath = sourceSets["functionalTest"].runtimeClasspath
    shouldRunAfter("test")
    useJUnitPlatform()
    systemProperty("user.language", "en")
    systemProperty("user.country", "US")
}

tasks.named("check") {
    dependsOn("functionalTest")
}

tasks.test {
    useJUnitPlatform()
}

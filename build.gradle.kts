import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
}

group = "no.esa"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    val springBoot = "org.springframework.boot"
    val jetbrains = "org.jetbrains.kotlin"
    val springFox = "io.springfox"
    val springFoxVersion = "3.0.0"

    // spring
    implementation(group = springBoot, name = "spring-boot-starter-web")
    implementation(group = springBoot, name = "spring-boot-starter-oauth2-client")
    implementation(group = springBoot, name = "spring-boot-starter-aop")
    implementation(group = springBoot, name = "spring-boot-starter-cache")
    developmentOnly(group = springBoot, name = "spring-boot-devtools")
    // implementation(group = "org.springframework.security", name = "spring-security-oauth2-client")

    testImplementation(group = springBoot, name = "spring-boot-starter-test")
    // Kotlin
    implementation(group = jetbrains, name = "kotlin-reflect")
    implementation(group = jetbrains, name = "kotlin-stdlib-jdk8")
    // Swagger
    implementation(group = springFox, name = "springfox-swagger-ui", version = springFoxVersion)
    implementation(group = springFox, name = "springfox-boot-starter", version = springFoxVersion)
    // Other
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(group = "io.vavr", name = "vavr", version = "0.10.3")


}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

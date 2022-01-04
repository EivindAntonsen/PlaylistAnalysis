import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("kapt") version "1.6.10"
}

group = "no.esa"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    val spring = object {
        val group = "org.springframework.boot"
        val web = "spring-boot-starter-web"
        val aop = "spring-boot-starter-aop"
        val oauth2Client = "spring-boot-starter-oauth2-client"
        val cache = "spring-boot-starter-cache"
        val security = "spring-boot-starter-security"
        val devtools = "spring-boot-devtools"
        val test = "spring-boot-starter-test"
    }
    val swagger = object {
        val group = "io.springfox"
        val ui = "springfox-swagger-ui"
        val bootStarter = "springfox-boot-starter"
        val version = "3.0.0"
    }
    val vavr = object {
        val group = "io.vavr"
        val vavr = "vavr"
        val version = "1.0.0-alpha-4"

    }
    val jetbrains = "org.jetbrains.kotlin"

    implementation(vavr.group, vavr.vavr, vavr.version)

    // spring
    implementation(spring.group, spring.web)
    implementation(spring.group, spring.oauth2Client)
    implementation(spring.group, spring.aop)
    implementation(spring.group, spring.cache)
    implementation(spring.group, spring.security)
    developmentOnly(spring.group, spring.devtools)
    testImplementation(spring.group, spring.test)

    // Kotlin
    implementation(group = jetbrains, name = "kotlin-reflect")
    implementation(group = jetbrains, name = "kotlin-stdlib-jdk8")
    // Swagger
    implementation(swagger.group, swagger.ui, version = swagger.version)
    implementation(swagger.group, swagger.bootStarter, version = swagger.version)
    // Other
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // https://mvnrepository.com/artifact/io.vavr/vavr
    implementation("io.vavr:vavr:1.0.0-alpha-4")

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

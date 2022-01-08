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
        val processor = "spring-boot-configuration-processor"
        val processorVersion = "2.6.2"
    }
    val swagger = object {
        val group = "io.springfox"
        val ui = "springfox-swagger-ui"
        val bootStarter = "springfox-boot-starter"
        val version = "3.0.0"
    }
    val jetbrains = object {
        val group = "org.jetbrains.kotlin"
        val reflect = "kotlin-reflect"
        val stdlib = "kotlin-stdlib-jdk8"
    }

    with(spring) {
        implementation(group, web)
        implementation(group, oauth2Client)
        implementation(group, aop)
        implementation(group, cache)
        implementation(group, security)
        developmentOnly(group, devtools)
        testImplementation(group, test)
        annotationProcessor(group, processor, processorVersion)
    }
    with(jetbrains) {
        implementation(group, reflect)
        implementation(group, stdlib)
    }
    with(swagger) {
        implementation(group, ui, version)
        implementation(group, bootStarter, version)
    }

    implementation(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin", version = "2.13.1")
    implementation(group = "io.vavr", name = "vavr", version = "1.0.0-alpha-4")
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

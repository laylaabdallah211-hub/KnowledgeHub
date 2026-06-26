plugins {
  kotlin("jvm") version "2.3.21"
  kotlin("plugin.spring") version "2.2.21"
  id("org.springframework.boot") version "4.0.6"
  id("io.spring.dependency-management") version "1.1.7"
  kotlin("plugin.noarg") version "2.3.0"
  id("dev.detekt") version "2.0.0-alpha.3"
}

group = "de.thm.mni"
version = "0.0.1-SNAPSHOT"
description = "KnowledgeHub"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(23)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webmvc")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-maven-noarg")
  implementation("tools.jackson.module:jackson-module-kotlin")

  // Adds jakarta validation api
  implementation("org.springframework.boot:spring-boot-starter-validation")

  // Database dependencies
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  runtimeOnly("com.h2database:h2") // Driver for in-memory h2 database
  runtimeOnly("org.postgresql:postgresql") // Driver for PostgreSQL database

  testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
  }
  noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
  }
}

sourceSets {
  val integrationTest by creating {
    kotlin {
      compileClasspath += sourceSets["main"].output + sourceSets["test"].output
      runtimeClasspath += sourceSets["main"].output + sourceSets["test"].output
      resources.srcDirs("./src/integrationTest/resources")
      kotlin.srcDirs("./src/integrationTest/kotlin")
    }
  }
}

tasks {
  val integrationTest by creating(Test::class) {
    description = "Run integration tests"
    group = "verification"
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
  }
}

configurations {
  named("integrationTestImplementation") {
    extendsFrom(configurations["testImplementation"])
  }
  named("integrationTestRuntimeOnly") {
    extendsFrom(configurations["testRuntimeOnly"])
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

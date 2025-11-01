import java.nio.file.Files
import java.nio.file.StandardCopyOption

plugins {
    kotlin("jvm") version "2.2.20"
    java
}

group = "io.github.rascmatt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
tasks.jar {
    archiveFileName.set("Coco.jar")

    manifest {
        from("src/main/java/coco/Coco.manifest")
    }

    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

tasks.register<JavaExec>("generateParser") {
    group = "build"
    description = "Generate parser using Coco/R"

    classpath = layout.buildDirectory.files("libs/Coco.jar")
    mainClass.set("coco.Coco")

    args("src/main/java/coco/Coco.atg")
    args("-o", "src/main/kotlin")
}
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
    args("-o", "src/main/java/coco")

    // The 'Parser.kt' is generated in the 'java' directory, so this moves it to 'kotlin'
    doLast {
        val original = file("src/main/kotlin/Parser.kt")
        if (original.exists()) {
            val renamed = file("src/main/kotlin/Parser.kt.old").toPath()
            Files.move(original.toPath(), renamed, StandardCopyOption.REPLACE_EXISTING)
        }

        val from = file("src/main/java/coco/Parser.kt").toPath()
        val to = file("src/main/kotlin/Parser.kt").toPath()
        Files.move(from, to, StandardCopyOption.REPLACE_EXISTING)
    }
}
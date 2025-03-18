plugins {
    java
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.0"
}

group = "gg.pufferfish"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files("lib/Carbon-API-1.8.8-R0.1-SNAPSHOT.jar")) // Any spigot that has public MapPalette.colors
}

val targetJavaVersion = 17
java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    targetCompatibility = javaVersion
    sourceCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
    }
}

tasks.compileJava {
    options.compilerArgs.add("--add-modules=jdk.incubator.vector")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            artifact(tasks["jar"])

            pom {
                name.set(project.name)
                description.set("Pufferfish SIMD operations")
                url.set("https://pufferfish.host/")
            }
        }
    }

    repositories {
        mavenLocal()
    }
}

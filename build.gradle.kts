plugins {
    kotlin("jvm") version "1.9.21"
}

group = "com.github.encryptsl.countryblocker"
version = providers.gradleProperty("plugin_version").get()
description = providers.gradleProperty("plugin_description").get()

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
    compileOnly(kotlin("stdlib", "1.9.21"))
    compileOnly("com.maxmind.geoip2:geoip2:4.0.1")
    testImplementation("com.maxmind.geoip2:geoip2:4.0.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks {
    test {
        useJUnitPlatform()
    }
    kotlin {
        jvmToolchain {
            (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
    processResources {
        filesMatching("plugin.yml") {
            expand(project.properties)
        }
    }
}
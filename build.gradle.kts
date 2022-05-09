import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.papermc.paperweight.userdev") version "1.3.6"
    kotlin("jvm") version "1.6.21"
}

group = "encryptsl.cekuj.net"
version = providers.gradleProperty("plugin_version").get()
description = providers.gradleProperty("plugin_description").get()

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    paperDevBundle(providers.gradleProperty("server_version").get())
    compileOnly(kotlin("stdlib", "1.6.21"))
    compileOnly("com.maxmind.geoip2:geoip2:3.0.1")
    testImplementation("com.maxmind.geoip2:geoip2:3.0.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks {
    reobfJar {
        outputJar.set(layout.buildDirectory.file("libs/${providers.gradleProperty("plugin_name").get()}-${version}.jar"))
    }
    test {
        useJUnitPlatform()
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
    processResources {
        filesMatching("plugin.yml") {
            expand(project.properties)
        }
    }
}
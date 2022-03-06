plugins {
    id("io.papermc.paperweight.userdev") version "1.3.5-LOCAL-SNAPSHOT"
    kotlin("jvm") version "1.6.10"
}

group = "encryptsl.cekuj.net"
version = providers.gradleProperty("plugin_version").get()
description = providers.gradleProperty("plugin_description").get()

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    paperDevBundle(providers.gradleProperty("server_version").get())
    compileOnly(kotlin("stdlib", "1.6.10"))
    compileOnly("com.maxmind.geoip2:geoip2:3.0.0")
    testImplementation("com.maxmind.geoip2:geoip2:3.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks {
    jar {
        archiveFileName.set(providers.gradleProperty("plugin_name").get() + "-" + archiveVersion.get()  + ".jar")
    }
    test {
        useJUnitPlatform()
    }
    processResources {
        filesMatching("plugin.yml") {
            expand(project.properties)
        }
    }
}
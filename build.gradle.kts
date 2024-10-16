@file:Suppress("SpellCheckingInspection")

import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("java")
    id("java-library")
    id("com.vanniktech.maven.publish").version("0.29.0")
}

group = "com.github.devlone64"
version = "1.0.8"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://libraries.minecraft.net/")
    maven("https://repo.codemc.io/repository/maven-public/")
    maven("https://repo.codemc.io/repository/maven-snapshots/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation("com.zaxxer", "HikariCP", "5.0.1")
    implementation("org.atteo.classindex", "classindex", "3.13")
    implementation("com.github.cryptomorin", "XSeries", "11.3.0")
    implementation("com.github.kevinsawicki", "http-request", "6.0")
    implementation("org.spongepowered", "configurate-yaml", "4.1.2")

    compileOnly("com.github.MilkBowl", "VaultAPI", "1.7")
    compileOnly("de.tr7zw", "item-nbt-api-plugin", "2.13.2")
    compileOnly("com.github.LoneDev6", "API-ItemsAdder", "3.6.1")

    compileOnly("org.xerial", "sqlite-jdbc", "3.46.1.0")

    compileOnly("net.kyori", "adventure-api", "4.13.0")
    compileOnly("net.kyori", "adventure-text-serializer-legacy", "4.13.0")

    compileOnly("com.mojang", "authlib", "6.0.54")
    compileOnly("commons-io", "commons-io", "2.17.0")
    compileOnly("org.projectlombok", "lombok", "1.18.32")
    compileOnly("org.jetbrains", "annotations", "20.1.0")
    compileOnly("org.apache.commons", "commons-lang3", "3.17.0")
    compileOnly("org.spigotmc", "spigot-api", "1.20.4-R0.1-SNAPSHOT")

    annotationProcessor("org.projectlombok", "lombok", "1.18.32")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    java.sourceCompatibility = JavaVersion.VERSION_17
    java.targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
    (options as StandardJavadocDocletOptions).addStringOption("encoding", "UTF-8")
    (options as StandardJavadocDocletOptions).addStringOption("charSet", "UTF-8")
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    coordinates("io.github.devlone64", "MSLib", "$version")

    pom {
        name = "MSLib"
        description = "The minecraft plugin utilities and libraries."
        url = "https://github.com/devlone64/MSLib"
        inceptionYear = "2024"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "devlone64"
                name = "lone64"
                url = "https://github.com/devlone64"
            }
        }
        scm {
            url.set("https://github.com/devlone64/MSLib")
            connection.set("scm:git:git://github.com/devlone64/MSLib.git")
            developerConnection.set("scm:git:ssh://git@github.com/devlone64/MSLib.git")
        }
    }
}
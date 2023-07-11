pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        kotlin("multiplatform").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
        id("io.ktor.plugin").version(extra["ktor.version"] as String)
        id("org.jetbrains.kotlin.plugin.serialization").version(extra["serialization.version"] as String)
    }
}

rootProject.name = "PortShadow"

include(":common")
include(":server")
include(":client:common", ":client:desktop")

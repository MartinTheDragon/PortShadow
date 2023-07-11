plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm {
        jvmToolchain(20)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                val exposedVersion = properties["exposed.version"] as String
                val h2Version = properties["h2.version"] as String
                implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
                implementation("com.h2database:h2:$h2Version")
            }
        }
    }
}

plugins {
    id("com.gradle.enterprise").version("3.8.1")
}

include(":bye-bye-dead-code")

apply(from = java.io.File(settingsDir, "buildCacheSettings.gradle"))

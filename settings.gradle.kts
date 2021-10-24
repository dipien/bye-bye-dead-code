plugins {
    id("com.gradle.enterprise").version("3.7.1")
}

include(":bye-bye-dead-code")

apply(from = java.io.File(settingsDir, "buildCacheSettings.gradle"))

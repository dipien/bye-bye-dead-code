plugins {
    id("com.gradle.enterprise").version("3.1.1")
}

include(":unused-android-code-gradle-plugin")

apply(from = java.io.File(settingsDir, "buildCacheSettings.gradle"))

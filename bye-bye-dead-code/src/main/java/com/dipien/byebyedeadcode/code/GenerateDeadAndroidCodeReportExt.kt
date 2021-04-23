package com.dipien.byebyedeadcode.code

open class GenerateDeadAndroidCodeReportExt {

    companion object {
        const val NAME = "deadAndroidCodeReport"
    }

    var verbose = false

    // Lists the code that was stripped by Proguard/R8
    var usagePath = "app/build/outputs/mapping/release/usage.txt"

    var reportPath = "android_dead_code.txt"

    var compiledKotlinClassesDir = "build/tmp/kotlin-classes/release"
    var compiledJavaClassesDir = "build/intermediates/javac/release"
    var generatedClassesDir = "build/generated/source/kapt/release"
}

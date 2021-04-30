package com.dipien.byebyedeadcode.code

import java.io.File

class FilterContext(
    private val compiledKotlinClassesDir: String,
    private val compiledJavaClassesDir: String,
    private val generatedClassesDir: String,
    private val srcDirs: List<String>
) {

    fun isCompiledKotlinClass(moduleName: String, targetPath: String): Boolean {
        return File("$moduleName/$compiledKotlinClassesDir/$targetPath.class").exists()
    }

    fun isCompiledJavaClass(moduleName: String, targetPath: String): Boolean {
        return File("$moduleName/$compiledJavaClassesDir/$targetPath.class").exists()
    }

    fun isGeneratedKotlinClass(moduleName: String, targetPath: String): Boolean {
        return File("$moduleName/$generatedClassesDir/$targetPath.kt").exists()
    }

    fun isGeneratedJAvaClass(moduleName: String, targetPath: String): Boolean {
        return File("$moduleName/$generatedClassesDir/$targetPath.java").exists()
    }

    fun isSrcCode(moduleName: String, targetPath: String): Boolean {
        for (srcDir in srcDirs) {
            if (File("$moduleName/$srcDir/$targetPath.kt").exists() ||
                    File("$moduleName/$srcDir/$targetPath.java").exists()) {
                return true
            }
        }
        return false
    }
}

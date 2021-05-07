package com.dipien.byebyedeadcode.code.filter

import java.io.File

class FilterContext(
    private val compiledKotlinClassesDir: String,
    private val compiledJavaClassesDir: String,
    private val generatedClassesDir: String,
    private val srcDirs: List<String>
) {

    fun createCompiledKotlinClass(moduleName: String, targetPath: String): File {
        return File("$moduleName/$compiledKotlinClassesDir/$targetPath.class")
    }

    fun createCompiledJavaClass(moduleName: String, targetPath: String): File {
        return File("$moduleName/$compiledJavaClassesDir/$targetPath.class")
    }

    fun isGeneratedKotlinClass(moduleName: String, targetPath: String): Boolean {
        return File("$moduleName/$generatedClassesDir/$targetPath.kt").exists()
    }

    fun isGeneratedJavaClass(moduleName: String, targetPath: String): Boolean {
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

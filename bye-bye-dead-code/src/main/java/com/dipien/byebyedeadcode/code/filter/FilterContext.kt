package com.dipien.byebyedeadcode.code.filter

import java.io.File

class FilterContext(
    private val compiledKotlinClassesDir: String,
    private val compiledJavaClassesDir: String,
    private val generatedClassesDirs: List<String>,
    val ignoredClasses: List<String>,
    val ignoredMembers: List<String>
) {

    fun createCompiledKotlinClass(moduleName: String, targetPath: String): File {
        return File("$moduleName/$compiledKotlinClassesDir/$targetPath.class")
    }

    fun createCompiledJavaClass(moduleName: String, targetPath: String): File {
        return File("$moduleName/$compiledJavaClassesDir/$targetPath.class")
    }

    fun isGeneratedKotlinClass(moduleName: String, targetPath: String): Boolean {
        return generatedClassesDirs.any { generatedClassesDir ->
            File("$moduleName/$generatedClassesDir/$targetPath.kt").exists()
        }
    }

    fun isGeneratedJavaClass(moduleName: String, targetPath: String): Boolean {
        return generatedClassesDirs.any { generatedClassesDir ->
            File("$moduleName/$generatedClassesDir/$targetPath.java").exists()
        }
    }

    fun isStoredInSrcDir(moduleName: String, targetPath: String): Boolean {
        val srcDir = "$moduleName/src"
        return File(srcDir).list()?.any {
            File("$srcDir/$it/java/$targetPath.kt").exists()
        } ?: false
    }
}

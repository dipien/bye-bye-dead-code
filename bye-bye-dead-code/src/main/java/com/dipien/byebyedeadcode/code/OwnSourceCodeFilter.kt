package com.dipien.byebyedeadcode.code

import com.dipien.byebyedeadcode.commons.LoggerHelper
import org.gradle.api.Project
import java.io.File

/**
 * Filters out kapt and library classes.
 */
class OwnSourceCodeFilter(
    project: Project,
    private val compiledKotlinClassesDir: String,
    private val compiledJavaClassesDir: String,
    private val generatedClassesDir: String
) : DeadCodeFilter {

    private val moduleNames: List<String>

    init {
        val names = mutableListOf<String>()
        project.subprojects {
            names.add(it.name)
        }
        moduleNames = names
    }

    override fun filter(deadCode: DeadCode): DeadCode? {
        var result: DeadCode? = null

        // For nested or inner classes if the root class is in the src directory then
        // the inner or nested class too. So, we are just going to check the root class.
        val classPath = deadCode.rootClassNameToPathAnnotation()
        moduleNames.forEach { moduleName ->
            // Kotlin
            if (File("$moduleName/$compiledKotlinClassesDir/$classPath.class").exists() &&
                    !File("$moduleName/$generatedClassesDir/$classPath.kt").exists()) {
                result = deadCode
                return@forEach
            }
            // Java
            if (File("$moduleName/$compiledJavaClassesDir/$classPath.class").exists() &&
                    !File("$moduleName/$generatedClassesDir/$classPath.java").exists()) {
                result = deadCode
                return@forEach
            }
        }
        if (result == null) {
            LoggerHelper.info("[SourceCodeFilter] Ignored: ${deadCode.className}")
        }
        return result
    }
}

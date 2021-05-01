package com.dipien.byebyedeadcode.code

import com.dipien.byebyedeadcode.commons.LoggerHelper
import org.gradle.api.Project

/**
 * Filters out kapt and library classes.
 */
class OwnSourceCodeFilter(
    project: Project,
    private val filterContext: FilterContext
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
        val targetPath = deadCode.rootClassNameToPathAnnotation()
        moduleNames.forEach { moduleName ->
            // Kotlin
            if (filterContext.isCompiledKotlinClass(moduleName, targetPath) &&
                    !filterContext.isGeneratedKotlinClass(moduleName, targetPath)) {
                deadCode.moduleName = moduleName
                result = deadCode
                return@forEach
            }
            // Java
            if (filterContext.isCompiledJavaClass(moduleName, targetPath) &&
                    !filterContext.isGeneratedJavaClass(moduleName, targetPath)) {
                deadCode.moduleName = moduleName
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

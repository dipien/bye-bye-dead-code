package com.dipien.byebyedeadcode.code.filter

import com.dipien.byebyedeadcode.code.DeadCode
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

        val targetPath = deadCode.classNameToPathAnnotation()
        moduleNames.forEach { moduleName ->
            // Kotlin
            val classFileKt = filterContext.createCompiledKotlinClass(moduleName, targetPath)
            if (classFileKt.exists() && !filterContext.isGeneratedKotlinClass(moduleName, targetPath)) {
                deadCode.moduleName = moduleName
                deadCode.classFile = classFileKt
                result = deadCode
                return@forEach
            }
            // Java
            val classFile = filterContext.createCompiledJavaClass(moduleName, targetPath)
            if (classFile.exists() && !filterContext.isGeneratedJavaClass(moduleName, targetPath)) {
                deadCode.moduleName = moduleName
                deadCode.classFile = classFile
                result = deadCode
                return@forEach
            }
        }
        if (result == null) {
            LoggerHelper.info("[OwnSourceCode] Ignored: ${deadCode.className}")
        }
        return result
    }
}

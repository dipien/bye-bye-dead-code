package com.jdroid.gradle.unusedandroidcode

import com.github.konifar.gradle.remover.UnusedResourcesRemoverExtension
import com.github.konifar.gradle.remover.util.ResultsReport
import com.jdroid.gradle.unusedandroidcode.commons.AbstractTask
import com.jdroid.gradle.unusedandroidcode.unusedresources.RemoveUnusedResourcesHelper
import java.lang.RuntimeException

open class CheckDebugOnProdTask : AbstractTask() {

    companion object {
        const val TASK_NAME = "checkDebugOnProd"
    }

    init {
        description = "Check if there are debug resources on main or release source sets"
    }

    override fun onExecute() {

        val unusedResourcesRemoverExtension = project.extensions.findByType(UnusedResourcesRemoverExtension::class.java)!!
        RemoveUnusedResourcesHelper.remove(project, unusedResourcesRemoverExtension)

        // Remove all the non production code
        project.rootProject.allprojects.forEach {
            it.file("src/debug").deleteRecursively()
            it.file("src/test").deleteRecursively()
            it.file("src/androidTest").deleteRecursively()
        }

        RemoveUnusedResourcesHelper.remove(project, unusedResourcesRemoverExtension)

        if (ResultsReport.getResults().isNotEmpty()) {
            project.logger.warn("**********************************************************************")
            project.logger.warn("The following files/resources should we moved to the debug source set:")
            ResultsReport.getResults().forEach {
                project.logger.warn(it)
            }
            project.logger.warn("**********************************************************************")
            throw RuntimeException("Some files should be moved to debug source set")
        } else {
            project.logger.lifecycle("No files/resources need to be moved to the debug source set")
        }
    }
}

package com.dipien.byebyedeadcode.sourceset

import com.dipien.byebyedeadcode.resources.util.ResultsReport
import com.dipien.byebyedeadcode.commons.AbstractTask
import com.dipien.byebyedeadcode.resources.RemoveUnusedResourcesHelper
import com.dipien.byebyedeadcode.resources.remover.AbstractRemover
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import java.lang.RuntimeException

open class CheckDebugOnProdTask : AbstractTask() {

    companion object {
        const val TASK_NAME = "checkDebugOnProd"
    }

    init {
        description = "Check if there are debug resources on main or release source sets"
    }

    @get:Input
    @get:Optional
    var androidUnusedResourcesExcludeNames: List<String> = emptyList()

    @get:Input
    @get:Optional
    var androidUnusedResourcesExtraRemovers: List<AbstractRemover> = emptyList()

    override fun onExecute() {

        RemoveUnusedResourcesHelper.remove(project, dryRun, androidUnusedResourcesExcludeNames, androidUnusedResourcesExtraRemovers)

        // Remove all the non production code
        project.rootProject.allprojects.forEach {
            it.file("src/debug").deleteRecursively()
            it.file("src/test").deleteRecursively()
            it.file("src/androidTest").deleteRecursively()
        }

        RemoveUnusedResourcesHelper.remove(project, dryRun, androidUnusedResourcesExcludeNames, androidUnusedResourcesExtraRemovers)

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

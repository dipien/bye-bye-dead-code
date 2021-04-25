package com.dipien.byebyedeadcode

import com.dipien.byebyedeadcode.code.RemoveUnusedAndroidCodeTask
import com.dipien.byebyedeadcode.commons.AbstractTask
import com.dipien.byebyedeadcode.commons.propertyResolver
import com.dipien.byebyedeadcode.resources.RemoveUnusedResourcesTask
import com.dipien.byebyedeadcode.sourceset.CheckDebugOnProdTask
import org.gradle.api.Plugin
import org.gradle.api.Project

open class ByeByeDeadCodePlugin : Plugin<Project> {

    companion object {
        const val EXTENSION_NAME = "byeByeDeadCode"
    }

    private lateinit var project: Project
    private lateinit var extension: ByeByeDeadCodeExtension

    override fun apply(project: Project) {
        this.project = project

        extension = project.extensions.create(EXTENSION_NAME, ByeByeDeadCodeExtension::class.java, project.propertyResolver)

        val removeUnusedAndroidCodeTask = project.tasks.create(RemoveUnusedAndroidCodeTask.TASK_NAME, RemoveUnusedAndroidCodeTask::class.java)
        project.afterEvaluate {
            init(removeUnusedAndroidCodeTask, extension)
        }

        val removeUnusedResourcesTask: RemoveUnusedResourcesTask = project.tasks.create(RemoveUnusedResourcesTask.TASK_NAME, RemoveUnusedResourcesTask::class.java)
        project.afterEvaluate {
            init(removeUnusedResourcesTask, extension)
            removeUnusedResourcesTask.extraUnusedResourcesRemovers = extension.extraUnusedResourcesRemovers
            removeUnusedResourcesTask.unusedResourcesExcludeNames = extension.unusedResourcesExcludeNames
        }

        val checkDebugOnProdTask = project.tasks.create(CheckDebugOnProdTask.TASK_NAME, CheckDebugOnProdTask::class.java)
        project.afterEvaluate {
            init(checkDebugOnProdTask, extension)
            checkDebugOnProdTask.extraUnusedResourcesRemovers = extension.extraUnusedResourcesRemovers
            checkDebugOnProdTask.unusedResourcesExcludeNames = extension.unusedResourcesExcludeNames
        }
    }

    private fun init(task: AbstractTask, extension: ByeByeDeadCodeExtension) {
        task.dryRun = extension.dryRun
    }
}

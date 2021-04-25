package com.dipien.byebyedeadcode

import com.dipien.byebyedeadcode.code.RemoveUnusedAndroidCodeTask
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

        project.tasks.create(RemoveUnusedAndroidCodeTask.TASK_NAME, RemoveUnusedAndroidCodeTask::class.java)
        project.tasks.create(RemoveUnusedResourcesTask.TASK_NAME, RemoveUnusedResourcesTask::class.java)
        project.tasks.create(CheckDebugOnProdTask.TASK_NAME, CheckDebugOnProdTask::class.java)
    }
}

package com.dipien.byebyedeadcode

import com.dipien.byebyedeadcode.code.RemoveUnusedAndroidCodeTask
import com.dipien.byebyedeadcode.resources.UnusedResourcesRemoverExtension
import com.dipien.byebyedeadcode.commons.propertyResolver
import com.dipien.byebyedeadcode.resources.RemoveUnusedResourcesTask
import com.dipien.byebyedeadcode.sourceset.CheckDebugOnProdTask
import org.gradle.api.Plugin
import org.gradle.api.Project

open class UnusedAndroidCodePlugin : Plugin<Project> {

    companion object {
        const val EXTENSION_NAME = "unusedAndroidCode"
    }

    private lateinit var project: Project
    private lateinit var extension: UnusedAndroidCodeExtension

    override fun apply(project: Project) {
        this.project = project

        extension = project.extensions.create(EXTENSION_NAME, UnusedAndroidCodeExtension::class.java, project.propertyResolver)

        project.extensions.create(UnusedResourcesRemoverExtension.NAME, UnusedResourcesRemoverExtension::class.java)

        project.tasks.create(RemoveUnusedAndroidCodeTask.TASK_NAME, RemoveUnusedAndroidCodeTask::class.java)
        project.tasks.create(RemoveUnusedResourcesTask.TASK_NAME, RemoveUnusedResourcesTask::class.java)
        project.tasks.create(CheckDebugOnProdTask.TASK_NAME, CheckDebugOnProdTask::class.java)
    }
}

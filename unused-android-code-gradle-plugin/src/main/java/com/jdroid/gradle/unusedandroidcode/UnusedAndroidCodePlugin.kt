package com.jdroid.gradle.unusedandroidcode

import com.dipien.unusedandroidcode.resources.UnusedResourcesRemoverExtension
import com.jdroid.gradle.unusedandroidcode.commons.propertyResolver
import com.jdroid.gradle.unusedandroidcode.unusedresources.RemoveUnusedResourcesTask
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

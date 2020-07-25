package com.jdroid.gradle.unusedandroidcode

import com.jdroid.gradle.unusedandroidcode.commons.propertyResolver
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
        project.tasks.create(RemoveUnusedAndroidCodeTask.TASK_NAME, RemoveUnusedAndroidCodeTask::class.java)
    }
}

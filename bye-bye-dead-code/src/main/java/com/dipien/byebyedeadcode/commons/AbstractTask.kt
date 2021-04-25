package com.dipien.byebyedeadcode.commons

import org.gradle.api.DefaultTask
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

abstract class AbstractTask : DefaultTask() {

    @get:Input
    var dryRun = false

    @get:Internal
    protected lateinit var commandExecutor: CommandExecutor

    init {
        group = "Code Quality"
    }

    @TaskAction
    fun doExecute() {
        commandExecutor = CommandExecutorImpl(project, LogLevel.LIFECYCLE)
        onExecute()
    }

    protected abstract fun onExecute()
}

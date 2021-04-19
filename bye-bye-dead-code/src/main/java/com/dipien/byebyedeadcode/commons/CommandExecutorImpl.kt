package com.dipien.byebyedeadcode.commons

import org.apache.tools.ant.types.Commandline
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import java.io.ByteArrayOutputStream
import java.io.File

class CommandExecutorImpl(private val project: Project, private val logLevel: LogLevel) : CommandExecutor {

    @Suppress("UNCHECKED_CAST")
    override fun execute(command: String, workingDirectory: File?, logStandardOutput: Boolean, logErrorOutput: Boolean, ignoreExitValue: Boolean): ExtendedExecResult {
        if (logStandardOutput) {
            log("Executing command: $command")
        }

        val workDir = workingDirectory ?: project.rootProject.projectDir

        val standardOutputStream = ByteArrayOutputStream()
        val errorOutputStream = ByteArrayOutputStream()

        val execResult = project.exec { execSpec ->
            execSpec.workingDir = workDir
            execSpec.setCommandLine(*Commandline.translateCommandline(command) as Array<Any>)
            execSpec.isIgnoreExitValue = true
            execSpec.standardOutput = standardOutputStream
            execSpec.errorOutput = errorOutputStream
        }
        if (logStandardOutput && standardOutputStream.size() > 0) {
            log(standardOutputStream.toString())
        }

        if (logErrorOutput && errorOutputStream.size() > 0) {
            project.logger.error(errorOutputStream.toString())
        }

        if (!ignoreExitValue && execResult.exitValue > 0) {
            throw RuntimeException("Failed execution of command: $command")
        }

        return ExtendedExecResult(execResult, standardOutputStream, errorOutputStream)
    }

    private fun log(message: String) {
        project.logger.log(logLevel, message)
    }
}

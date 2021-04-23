package com.dipien.byebyedeadcode.code

import com.dipien.byebyedeadcode.commons.AbstractTask
import com.dipien.byebyedeadcode.commons.LoggerHelper

open class GenerateDeadAndroidCodeReportTask : AbstractTask() {

    companion object {
        const val TASK_NAME = "generateDeadAndroidCodeReport"
    }

    init {
        description = "Generate report of dead android code"
    }

    override fun onExecute() {
        val extension = project.extensions.findByType(GenerateDeadAndroidCodeReportExt::class.java)!!
        this.verbose = extension.verbose

        LoggerHelper.info("usagePath: ${extension.usagePath}")
        LoggerHelper.info("reportPath: ${extension.reportPath}")
        LoggerHelper.info("compiledKotlinClassesDir: ${extension.compiledKotlinClassesDir}")
        LoggerHelper.info("compiledJavaClassesDir: ${extension.compiledJavaClassesDir}")
        LoggerHelper.info("generatedClassesDir: ${extension.generatedClassesDir}")

        val deadCodeFilterHelper = DeadCodeFilterHelper(
                project,
                extension.compiledKotlinClassesDir,
                extension.compiledJavaClassesDir,
                extension.generatedClassesDir
        )
        ReportGenerator(deadCodeFilterHelper).generate(extension.usagePath, extension.reportPath)
    }
}

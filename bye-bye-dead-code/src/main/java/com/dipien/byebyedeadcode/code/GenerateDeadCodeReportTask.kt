package com.dipien.byebyedeadcode.code

import com.dipien.byebyedeadcode.commons.AbstractTask
import com.dipien.byebyedeadcode.commons.LoggerHelper
import org.gradle.api.tasks.Input

open class GenerateDeadCodeReportTask : AbstractTask() {

    companion object {
        const val TASK_NAME = "generateDeadCodeReport"
    }

    init {
        description = "Generate dead code report"
    }

    @get:Input
    lateinit var proguardUsageFilePath: String

    @get:Input
    lateinit var reportFilePath: String

    @get:Input
    lateinit var compiledKotlinClassesDir: String

    @get:Input
    lateinit var compiledJavaClassesDir: String

    @get:Input
    lateinit var generatedClassesDir: String

    override fun onExecute() {
        LoggerHelper.info("proguardUsageFilePath: $proguardUsageFilePath")
        LoggerHelper.info("reportFilePath: $reportFilePath")
        LoggerHelper.info("compiledKotlinClassesDir: $compiledKotlinClassesDir")
        LoggerHelper.info("compiledJavaClassesDir: $compiledJavaClassesDir")
        LoggerHelper.info("generatedClassesDir: $generatedClassesDir")

        val deadCodeFilterHelper = DeadCodeFilterHelper(
                project,
                compiledKotlinClassesDir,
                compiledJavaClassesDir,
                generatedClassesDir
        )
        ReportGenerator(deadCodeFilterHelper).generate(proguardUsageFilePath, reportFilePath)
    }
}

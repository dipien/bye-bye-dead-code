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

        val deadCodeFilter = DeadCodeFilterHelper(project, compiledKotlinClassesDir, compiledJavaClassesDir, generatedClassesDir)
        val deadCodeReporter = DeadCodeReporter(reportFilePath)
        UsageFileParser(proguardUsageFilePath).parse { deadCode ->
            val filteredDeadCode = deadCodeFilter.filter(deadCode)
            filteredDeadCode?.let {
                // If the class has no members then it is a dead class and can be completely removed
                if (it.classMembers.isEmpty()) {
                    deadCodeReporter.addDeadClass(it)
                } else {
                    deadCodeReporter.addClassWithDeadCode(it)
                }
            }
        }
        deadCodeReporter.writeReport()
    }
}
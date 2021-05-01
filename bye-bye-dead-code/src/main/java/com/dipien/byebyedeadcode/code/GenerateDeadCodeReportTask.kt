package com.dipien.byebyedeadcode.code

import com.dipien.byebyedeadcode.code.filter.DeadCodeFilterHelper
import com.dipien.byebyedeadcode.code.filter.FilterContext
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

    @get:Input
    lateinit var srcDirs: List<String>

    override fun onExecute() {
        LoggerHelper.info("proguardUsageFilePath: $proguardUsageFilePath")
        LoggerHelper.info("reportFilePath: $reportFilePath")
        LoggerHelper.info("compiledKotlinClassesDir: $compiledKotlinClassesDir")
        LoggerHelper.info("compiledJavaClassesDir: $compiledJavaClassesDir")
        LoggerHelper.info("generatedClassesDir: $generatedClassesDir")
        LoggerHelper.info("srcDirs: $srcDirs")

        val filterContext = FilterContext(compiledKotlinClassesDir, compiledJavaClassesDir, generatedClassesDir, srcDirs)
        val deadCodeFilter = DeadCodeFilterHelper(project, filterContext)
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

package com.dipien.byebyedeadcode.code

import java.io.File

class ReportGenerator(private val deadCodeFilterHelper: DeadCodeFilterHelper) {

    private val usageFileParser = UsageFileParser()

    private val deadClasses = mutableListOf<DeadCode>()
    private val classesWithDeadCode = mutableListOf<DeadCode>()

    fun generate(usageFilePath: String, reportFilePath: String) {
        removeReportFile(reportFilePath)
        File(usageFilePath).forEachLine { line ->
            usageFileParser.parse(line)
            usageFileParser.next()?.let { deadCode ->
                process(deadCode)
            }
        }
        process(usageFileParser.next(true)!!)
        writeReport(reportFilePath)
    }

    private fun process(deadCode: DeadCode) {
        val filteredDeadCode = deadCodeFilterHelper.filter(deadCode)
        filteredDeadCode?.let {
            // If class has not members then it is a dead class and can be completely removed
            if (it.classMembers.isEmpty()) {
                deadClasses.add(it)
            } else {
                classesWithDeadCode.add(it)
            }
        }
    }

    private fun removeReportFile(reportFilePath: String) {
        val file = File(reportFilePath)
        if (file.exists()) {
            file.delete()
        }
    }

    private fun createReportFile(reportFilePath: String): File {
        val reportFile = File(reportFilePath)
        val dir = reportFile.parentFile
        if (dir != null && !dir.exists()) {
            dir.mkdirs()
        }
        reportFile.createNewFile()
        return reportFile
    }

    private fun writeReport(reportFilePath: String) {
        val reportFile = createReportFile(reportFilePath)
        deadClasses.sortBy { it.className }
        classesWithDeadCode.sortBy { it.className }
        deadClasses.forEach {
            writeReport(reportFile, it)
        }
        classesWithDeadCode.forEach {
            writeReport(reportFile, it)
        }
    }

    private fun writeReport(reportFile: File, deadCode: DeadCode) {
        reportFile.appendText(deadCode.className)
        reportFile.appendText(System.lineSeparator())
        deadCode.classMembers.forEach { member ->
            reportFile.appendText(member)
            reportFile.appendText(System.lineSeparator())
        }
    }
}

package com.dipien.byebyedeadcode.code

import java.io.File

class ReportGenerator(private val deadCodeFilterHelper : DeadCodeFilterHelper) {

    private val usageFileParser = UsageFileParser()

    private val deadClasses = mutableListOf<DeadCode>()
    private val classesWithDeadCode = mutableListOf<DeadCode>()

    fun generate(usagePath : String, reportPath : String) {
        removeReportFile(reportPath)
        File(usagePath).forEachLine { line ->
            usageFileParser.parse(line)
            usageFileParser.next()?.let { deadCode ->
                process(deadCode)
            }
        }
        process(usageFileParser.next(true)!!)
        writeReport(reportPath)
    }

    private fun process(deadCode : DeadCode) {
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

    private fun removeReportFile(reportPath : String) {
        val file = File(reportPath)
        if (file.exists()) {
            file.delete()
        }
    }

    private fun createReportFile(reportPath : String) : File {
        val reportFile = File(reportPath)
        val dir = reportFile.parentFile
        if (dir != null && !dir.exists()) {
            dir.mkdirs()
        }
        reportFile.createNewFile()
        return reportFile
    }

    private fun writeReport(reportPath : String) {
        val reportFile = createReportFile(reportPath)
        deadClasses.sortBy { it.className }
        classesWithDeadCode.sortBy { it.className }
        deadClasses.forEach {
            writeReport(reportFile, it)
        }
        classesWithDeadCode.forEach {
            writeReport(reportFile, it)
        }
    }

    private fun writeReport(reportFile : File, deadCode : DeadCode) {
        reportFile.appendText(deadCode.className)
        reportFile.appendText(System.lineSeparator())
        deadCode.classMembers.forEach { member ->
            reportFile.appendText(member)
            reportFile.appendText(System.lineSeparator())
        }
    }

}
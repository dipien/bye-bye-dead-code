package com.dipien.byebyedeadcode.code

import java.io.File

class DeadCodeReporter(private val reportFilePath: String) {

    init {
        removePreviousReport()
    }

    private val deadClasses = mutableListOf<DeadCode>()
    private val classesWithDeadCode = mutableListOf<DeadCode>()

    fun addDeadClass(deadClass: DeadCode) {
        deadClasses.add(deadClass)
    }

    fun addClassWithDeadCode(classWithDeadCode: DeadCode) {
        classesWithDeadCode.add(classWithDeadCode)
    }

    private fun removePreviousReport() {
        val file = File(reportFilePath)
        if (file.exists()) {
            file.delete()
        }
    }

    private fun createReportFile(): File {
        val reportFile = File(reportFilePath)
        val dir = reportFile.parentFile
        if (dir != null && !dir.exists()) {
            dir.mkdirs()
        }
        reportFile.createNewFile()
        return reportFile
    }

    fun writeReport() {
        val reportFile = createReportFile()
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

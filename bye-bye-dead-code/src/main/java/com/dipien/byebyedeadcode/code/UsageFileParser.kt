package com.dipien.byebyedeadcode.code

import java.io.File

class UsageFileParser(proguardUsageFilePath: String) {

    private val proguardUsageFile = File(proguardUsageFilePath)

    private var ready: DeadCode? = null

    private var className: String? = null
    private lateinit var classMembers: MutableList<String>

    private fun parseLine(line: String) {
        if (isClassName(line)) {
            if (className != null) {
                ready = DeadCode(className!!, classMembers)
            }
            className = line.removeSuffix(":")
            classMembers = mutableListOf()
        } else {
            classMembers.add(line)
        }
    }

    private fun next(isEOF: Boolean = false): DeadCode? {
        val result = if (isEOF) DeadCode(className!!, classMembers) else ready
        ready = null
        return result
    }

    fun parse(block: (DeadCode) -> Unit) {
        proguardUsageFile.forEachLine { line ->
            parseLine(line)
            next()?.let { deadCode ->
                block(deadCode)
            }
        }
        block(next(true)!!)
    }

    // If string starts with a letter then it's a class name. Class members start with spaces.
    private fun isClassName(line: String): Boolean = line.matches("^[a-z].+".toRegex())
}

package com.dipien.byebyedeadcode.code

class UsageFileParser {

    private var ready : DeadCode? = null

    private var className : String? = null
    private lateinit var classMembers : MutableList<String>

    fun parse(line : String) {
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

    fun next(isEOF : Boolean = false) : DeadCode? {
        val result = if (isEOF) DeadCode(className!!, classMembers) else ready
        ready = null
        return result
    }

    // If string starts with a letter then it's a class name. Class members start with spaces.
    private fun isClassName(line : String) : Boolean = line.matches("^[a-z].+".toRegex())

}
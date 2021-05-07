package com.dipien.byebyedeadcode.code.filter

import com.dipien.byebyedeadcode.code.DeadCode
import com.dipien.byebyedeadcode.commons.LoggerHelper

class ClassMemberFilter(private val regex: Regex, private val name: String) : DeadCodeFilter {

    override fun filter(deadCode: DeadCode): DeadCode? {
        if (deadCode.classMembers.isEmpty()) {
            return deadCode
        }

        var isFirstIgnored = true
        deadCode.classMembers.removeIf {
            val ignore = it.matches(regex)
            if (ignore) {
                if (isFirstIgnored) {
                    LoggerHelper.info("[$name] Members ignored for class: ${deadCode.className}")
                    isFirstIgnored = false
                }
                LoggerHelper.info("[$name] $it")
            }
            ignore
        }

        return if (deadCode.classMembers.isEmpty()) {
            LoggerHelper.info("[$name] No members. Ignored: ${deadCode.className}")
            null
        } else {
            deadCode
        }
    }
}

package com.dipien.byebyedeadcode.code.filter

import com.dipien.byebyedeadcode.code.DeadCode
import com.dipien.byebyedeadcode.commons.LoggerHelper

class ClassMemberFilter(private val regex: Regex, private val name: String) : DeadCodeFilter {

    override fun filter(deadCode: DeadCode): DeadCode? {
        if (deadCode.classMembers.isEmpty()) {
            return deadCode
        }

        val membersToRemove = deadCode.classMembers.filter { it.matches(regex) }
        if (membersToRemove.isNotEmpty()) {
            LoggerHelper.info("[$name] Members ignored for class: ${deadCode.className}")
            membersToRemove.forEach {
                deadCode.classMembers.remove(it)
                LoggerHelper.info("[$name] $it")
            }
        }
        return if (deadCode.classMembers.size == membersToRemove.size) {
            LoggerHelper.info("[$name] No members. Ignored: ${deadCode.className}")
            null
        } else {
            deadCode
        }
    }
}

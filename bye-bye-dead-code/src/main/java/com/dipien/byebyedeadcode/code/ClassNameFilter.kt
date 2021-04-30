package com.dipien.byebyedeadcode.code

import com.dipien.byebyedeadcode.commons.LoggerHelper

class ClassNameFilter(private val regex: Regex, private val name: String) : DeadCodeFilter {

    override fun filter(deadCode: DeadCode): DeadCode? {
        return if (deadCode.className.matches(regex)) {
            LoggerHelper.info("[$name] Ignored: ${deadCode.className}")
            null
        } else {
            deadCode
        }
    }
}

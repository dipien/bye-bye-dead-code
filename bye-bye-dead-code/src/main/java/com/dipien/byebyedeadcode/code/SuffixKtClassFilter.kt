package com.dipien.byebyedeadcode.code

import com.dipien.byebyedeadcode.commons.LoggerHelper

class SuffixKtClassFilter(private val filterContext: FilterContext) : DeadCodeFilter {

    private val regex = ".+Kt\$".toRegex()

    override fun filter(deadCode: DeadCode): DeadCode? {
        // If the class name ends in "Kt", it could be a generated class or a
        // class in the source code. But if there is a file name without
        // the suffix "kt" in the source code then the class is a generated class
        val targetPath = deadCode.classNameToPathAnnotation().substringBefore("Kt")
        return if (deadCode.className.matches(regex) &&
                filterContext.isSrcCode(deadCode.moduleName, targetPath)) {
            LoggerHelper.info("[SuffixKtClassFilter] Ignored: ${deadCode.className}")
            return null
        } else {
            deadCode
        }
    }
}

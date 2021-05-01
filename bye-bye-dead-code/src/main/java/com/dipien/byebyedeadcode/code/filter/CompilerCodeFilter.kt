package com.dipien.byebyedeadcode.code.filter

import com.dipien.byebyedeadcode.code.DeadCode

class CompilerCodeFilter(filterContext: FilterContext) : DeadCodeFilter {

    companion object {
        // Class Filters
        // e.g: 'com.example.MyClass$1'
        const val ANONYMOUS_CLASS = ".+\\\$\\d+\$"
        // e.g: 'com.example.MyInterface$DefaultImpls'
        const val DEFAULT_IMPLES = ".+\\\$DefaultImpls\$"
    }

    private val filters = listOf(
            // Class Filters
            ClassNameFilter(ANONYMOUS_CLASS.toRegex(), "AnonymousClassFilter"),
            ClassNameFilter(DEFAULT_IMPLES.toRegex(), "DefaultImplsFilter"),
            SuffixKtClassFilter(filterContext)
    )

    override fun filter(deadCode: DeadCode): DeadCode? {
        var result: DeadCode? = deadCode
        for (regexFilter in filters) {
            result = regexFilter.filter(result!!)
            if (result == null) {
                break
            }
        }
        return result
    }
}

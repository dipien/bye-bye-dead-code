package com.dipien.byebyedeadcode.code.filter

import com.dipien.byebyedeadcode.code.DeadCode

class CompilerCodeFilter(filterContext: FilterContext) : DeadCodeFilter {

    companion object {
        // Class Filters
        // e.g: 'com.example.MyClass$1'
        const val ANONYMOUS_CLASS = ".+\\\$\\d+\$"
        // e.g: 'com.example.MyInterface$DefaultImpls'
        const val DEFAULT_IMPLES = ".+\\\$DefaultImpls\$"

        // Member Filters
        // e.g: 'public final java.lang.String component1()'
        const val COMPONENT_N_FUNCTION = ".+final.+component\\d+\\(\\)"
    }

    private val filters = listOf(
            // Class Filters
            ClassNameFilter(ANONYMOUS_CLASS.toRegex(), "AnonymousClass"),
            ClassNameFilter(DEFAULT_IMPLES.toRegex(), "DefaultImpls"),
            SuffixKtClassFilter(filterContext),

            // Member Filters
            ClassMemberFilter(COMPONENT_N_FUNCTION.toRegex(), "ComponentNFunction")
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

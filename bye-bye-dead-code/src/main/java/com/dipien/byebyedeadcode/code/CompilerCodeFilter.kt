package com.dipien.byebyedeadcode.code

class CompilerCodeFilter : DeadCodeFilter {

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
            ClassNameFilter(DEFAULT_IMPLES.toRegex(), "DefaultImplsFilter")
    )

    override fun filter(deadCode: DeadCode): DeadCode? {
        var result: DeadCode? = deadCode
        filters.forEach { regexFilter ->
            result?.let {
                result = regexFilter.filter(it)
                if (result == null) {
                    return@forEach
                }
            }
        }
        return result
    }
}

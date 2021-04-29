package com.dipien.byebyedeadcode.code

class CompilerCodeFilter : DeadCodeFilter {

    companion object {
        // Class Filters
        // e.g: 'com.example.MyClass$1'
        const val ANONYMOUS_CLASS = ".+\\\$\\d+\$"
        // e.g: 'com.example.MyInterface$DefaultImpls'
        const val DEFAULT_IMPLES = ".+\\\$DefaultImpls\$"

        @JvmStatic
        fun main(args: Array<String>) {
            foo()
        }

        fun foo() {
            listOf(1, 2, 3, 4, 5).forEach lit@{
                if (it == 3) return@lit // local return to the caller of the lambda - the forEach loop
                print(it)
            }
            print(" done with explicit label")
        }
    }

    private val filters = listOf(
            // Class Filters
            ClassNameFilter(ANONYMOUS_CLASS.toRegex(), "AnonymousClassFilter"),
            ClassNameFilter(DEFAULT_IMPLES.toRegex(), "DefaultImplsFilter")
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

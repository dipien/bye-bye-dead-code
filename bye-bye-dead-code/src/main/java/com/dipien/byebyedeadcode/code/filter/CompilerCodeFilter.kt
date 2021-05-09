package com.dipien.byebyedeadcode.code.filter

import com.dipien.byebyedeadcode.code.DeadCode

class CompilerCodeFilter(filterContext: FilterContext) : DeadCodeFilter {

    companion object {
        // Class Filters
        // e.g: 'com.example.MyClass$1'
        const val ANONYMOUS_CLASS = """.+\$\d+$"""
        // e.g: 'com.example.MyInterface$DefaultImpls'
        const val DEFAULT_IMPLES = """.+\${'$'}DefaultImpls$"""

        // Member Filters
        // e.g: 'public final java.lang.String component1()'
        const val COMPONENT_N_FUNCTION = """.+final.+component\d+\(\)"""
        // e.g: 'public com.example.MyClass copy(java.lang.String)'
        // e.g: 'public final com.example.MyClass copy$default(java.lang.String)'
        const val COPY_FUNCTION = """.+ copy(\${'$'}default)?\(.+\)"""
        // e.g: 'public final com.example.MyClass getMyClass()'
        // e.g: 'public final boolean isEnabled()'
        const val GET_FUNCTION = """.+ ([bB]oolean is|get).+\(\)"""
        // e.g: 'public final void setMyClass(com.example.MyClass)'
        const val SET_FUNCTION = """.+ set[A-Z].+\(.+\)"""
        // e.g: 'public static final com.example.MyClass$Companion Companion'
        const val COMPANION_FIELD = """.+\${'$'}Companion Companion$"""
        // e.g: 'public static final synthetic void access$setResumed$cp(int)'
        const val ACCESS_FUNCTION = """^.+static final.+ access\$.+"""
        /**
         * Proguard removes some static variables because the bytecode does not
         * use them, the compiler replaces its values in each place where the
         * variable is used. So we do not know which of them are not used in
         * the source code.
         */
        // e.g: 'private static final java.lang.String EXAMPLE'
        const val STATIC_FIELD = """^.+static final[^)]+$"""
        // e.g: 'public void <init>(com.example.MyClass)'
        const val CONSTRUCTOR_FUNCTION = """.*void <init>\(.*"""
        // e.g: 'static synthetic void init$default(com.example.MyClass)'
        const val DEFAULT_FUNCTION = """.*\${'$'}default\(.*"""
    }

    private val filters = listOf(
            // Class Filters
            ClassNameFilter(ANONYMOUS_CLASS.toRegex(), "AnonymousClass"),
            ClassNameFilter(DEFAULT_IMPLES.toRegex(), "DefaultImpls"),
            SuffixKtClassFilter(filterContext),

            // Member Filters
            ClassMemberFilter(COMPONENT_N_FUNCTION.toRegex(), "ComponentNFunction"),
            ClassMemberFilter(COPY_FUNCTION.toRegex(), "CopyFunction"),
            ClassMemberFilter(GET_FUNCTION.toRegex(), "GetFunction"),
            ClassMemberFilter(SET_FUNCTION.toRegex(), "SetFunction"),
            ClassMemberFilter(COMPANION_FIELD.toRegex(), "CompanionField"),
            ClassMemberFilter(ACCESS_FUNCTION.toRegex(), "AccessFunction"),
            ClassMemberFilter(STATIC_FIELD.toRegex(), "StaticField"),
            ClassMemberFilter(CONSTRUCTOR_FUNCTION.toRegex(), "ConstructorFunction"),
            ClassMemberFilter(DEFAULT_FUNCTION.toRegex(), "DefaultFunction")
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

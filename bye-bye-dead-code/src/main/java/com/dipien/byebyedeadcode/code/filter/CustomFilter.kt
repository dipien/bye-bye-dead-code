package com.dipien.byebyedeadcode.code.filter

import com.dipien.byebyedeadcode.code.DeadCode

class CustomFilter(ignoredClasses: List<String>, ignoredMembers: List<String>) : DeadCodeFilter {

    private var filters: List<DeadCodeFilter>

    init {
        val filtersAux = mutableListOf<DeadCodeFilter>()
        ignoredClasses.forEach {
            filtersAux.add(ClassNameFilter(it.toRegex(), "CustomClassFilter"))
        }
        ignoredMembers.forEach {
            filtersAux.add(ClassMemberFilter(it.toRegex(), "CustomMemberFilter"))
        }
        this.filters = filtersAux
    }

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

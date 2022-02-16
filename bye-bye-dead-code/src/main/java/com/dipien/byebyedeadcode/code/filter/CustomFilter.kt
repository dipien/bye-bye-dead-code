package com.dipien.byebyedeadcode.code.filter

import com.dipien.byebyedeadcode.code.DeadCode

class CustomFilter(ignore: List<String>) : DeadCodeFilter {

    private var filters: List<DeadCodeFilter>

    init {
        val filtersAux = mutableListOf<DeadCodeFilter>()
        ignore.forEach {
            val regex = it.toRegex()
            filtersAux.add(ClassNameFilter(regex, "RegexFilter"))
            filtersAux.add(ClassMemberFilter(regex, "RegexFilter"))
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

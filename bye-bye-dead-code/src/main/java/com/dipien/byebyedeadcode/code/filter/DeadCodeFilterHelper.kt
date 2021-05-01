package com.dipien.byebyedeadcode.code.filter

import com.dipien.byebyedeadcode.code.DeadCode
import org.gradle.api.Project

class DeadCodeFilterHelper(
    project: Project,
    filterContext: FilterContext
) {

    private val filters = listOf(
            OwnSourceCodeFilter(project, filterContext),
            CompilerCodeFilter(filterContext)
    )

    fun filter(deadCode: DeadCode): DeadCode? {
        var result: DeadCode? = deadCode
        for (deadCodeFilter in filters) {
            if (result == null) {
                break
            }
            result = deadCodeFilter.filter(result)
        }
        return result
    }
}

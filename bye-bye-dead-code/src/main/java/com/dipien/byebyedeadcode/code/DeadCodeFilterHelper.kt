package com.dipien.byebyedeadcode.code

import org.gradle.api.Project

class DeadCodeFilterHelper(project : Project,
                           compiledKotlinClassesDir : String,
                           compiledJavaClassesDir : String,
                           generatedClassesDir : String) {

    private val filters = listOf<DeadCodeFilter>(
            OwnSourceCodeFilter(project, compiledKotlinClassesDir, compiledJavaClassesDir, generatedClassesDir)
            // TODO add filters
    )

    fun filter(deadCode : DeadCode) : DeadCode? {
        var result : DeadCode? = deadCode
        for (deadCodeFilter in filters) {
            if (result == null) {
                break
            }
            result = deadCodeFilter.filter(result)
        }
        return result
    }

}
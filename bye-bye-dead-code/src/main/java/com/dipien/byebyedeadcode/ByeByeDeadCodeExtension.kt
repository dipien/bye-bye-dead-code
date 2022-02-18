package com.dipien.byebyedeadcode

import com.dipien.byebyedeadcode.commons.PropertyResolver
import com.dipien.byebyedeadcode.resources.remover.AbstractRemover

open class ByeByeDeadCodeExtension(propertyResolver: PropertyResolver) {

    var verbose = false

    var androidUnusedResourcesExtraRemovers: List<AbstractRemover> = emptyList()

    var androidUnusedResourcesExcludeNames: List<String> = emptyList()

    var dryRun: Boolean = propertyResolver.getRequiredBooleanProp(::dryRun.name, false)

    // Lists the code that was stripped by R8
    var r8UsageFilePath = "app/build/outputs/mapping/release/usage.txt"

    var reportFilePath = "app/build/outputs/dead_code/usage.txt"

    // Module dirs
    var compiledKotlinClassesDir = "build/tmp/kotlin-classes/release"
    var compiledJavaClassesDir = "build/intermediates/javac/release"
    var generatedClassesDirs = listOf(
        "build/generated/source/kapt/release",
        "build/generated/source/navigation-args/release"
    )

    var ignoredClasses = listOf<String>()
    var ignoredMembers = listOf<String>()
}

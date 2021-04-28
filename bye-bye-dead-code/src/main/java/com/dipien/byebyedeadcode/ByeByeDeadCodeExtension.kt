package com.dipien.byebyedeadcode

import com.dipien.byebyedeadcode.commons.PropertyResolver
import com.dipien.byebyedeadcode.resources.remover.AbstractRemover

open class ByeByeDeadCodeExtension(propertyResolver: PropertyResolver) {

    var verbose = false

    var extraUnusedResourcesRemovers: List<AbstractRemover> = emptyList()

    var unusedResourcesExcludeNames: List<String> = emptyList()

    var dryRun: Boolean = propertyResolver.getRequiredBooleanProp(::dryRun.name, false)

    // Lists the code that was stripped by Proguard/R8
    var proguardUsageFilePath = "app/build/outputs/mapping/release/usage.txt"

    var reportFilePath = "android_dead_code.txt"

    var compiledKotlinClassesDir = "build/tmp/kotlin-classes/release"
    var compiledJavaClassesDir = "build/intermediates/javac/release"
    var generatedClassesDir = "build/generated/source/kapt/release"
}

package com.dipien.byebyedeadcode

import com.dipien.byebyedeadcode.commons.PropertyResolver
import com.dipien.byebyedeadcode.resources.remover.AbstractRemover

open class ByeByeDeadCodeExtension(propertyResolver: PropertyResolver) {

    var extraUnusedResourcesRemovers: List<AbstractRemover> = emptyList()

    var unusedResourcesExcludeNames: List<String> = emptyList()

    var dryRun: Boolean = propertyResolver.getRequiredBooleanProp(::dryRun.name, false)
}

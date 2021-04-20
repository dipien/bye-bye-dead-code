package com.dipien.byebyedeadcode

import com.dipien.byebyedeadcode.commons.PropertyResolver

open class ByeByeDeadCodeExtension(propertyResolver: PropertyResolver) {

    var sample: String = propertyResolver.getRequiredStringProp(::sample.name, "sample")
}

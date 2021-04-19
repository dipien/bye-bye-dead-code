package com.dipien.unusedandroidcode

import com.dipien.unusedandroidcode.commons.PropertyResolver

open class UnusedAndroidCodeExtension(propertyResolver: PropertyResolver) {

    var sample: String = propertyResolver.getRequiredStringProp(::sample.name, "sample")
}

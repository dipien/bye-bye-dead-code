package com.jdroid.gradle.unusedandroidcode

import com.jdroid.gradle.unusedandroidcode.commons.PropertyResolver

open class UnusedAndroidCodeExtension(propertyResolver: PropertyResolver) {

    var sample: String = propertyResolver.getRequiredStringProp(::sample.name, "sample")
}

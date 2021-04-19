package com.dipien.unusedandroidcode.commons

import org.gradle.api.Project

val Project.propertyResolver: PropertyResolver
    get() = PropertyResolverImpl(this)

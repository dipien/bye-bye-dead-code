package com.jdroid.gradle.unusedandroidcode.commons

import org.gradle.api.Project

val Project.propertyResolver: PropertyResolver
    get() = PropertyResolverImpl(this)

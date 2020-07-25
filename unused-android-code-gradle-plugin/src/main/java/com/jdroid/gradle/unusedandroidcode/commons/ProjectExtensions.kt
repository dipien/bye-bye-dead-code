package com.jdroid.gradle.unusedandroidcode.commons

import org.gradle.api.Project

private val propertyResolverCache = mutableMapOf<Project, PropertyResolver>()

val Project.propertyResolver: PropertyResolver
    get() = propertyResolverCache.getOrPut(this) { PropertyResolverImpl(this) }

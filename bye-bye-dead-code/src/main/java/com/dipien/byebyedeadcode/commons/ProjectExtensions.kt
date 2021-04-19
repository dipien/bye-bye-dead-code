package com.dipien.byebyedeadcode.commons

import org.gradle.api.Project

val Project.propertyResolver: PropertyResolver
    get() = PropertyResolverImpl(this)

package com.dipien.byebyedeadcode.code

import java.io.File

data class DeadCode(
    val className: String,
    val classMembers: MutableList<String>
) {

    lateinit var moduleName: String
    lateinit var classFile: File

    fun classNameToPathAnnotation(): String = className.replace(".", "/")
}

package com.dipien.byebyedeadcode.code

import org.objectweb.asm.ClassReader
import java.io.File

data class DeadCode(
    val className: String,
    val classMembers: MutableList<String>
) {

    lateinit var moduleName: String
    lateinit var classFile: File

    val classReader: ClassReader by lazy { ClassReader(classFile.readBytes()) }

    fun classNameToPathAnnotation(): String = className.replace(".", "/")
}

package com.dipien.byebyedeadcode.code.filter

import com.dipien.byebyedeadcode.code.DeadCode
import com.dipien.byebyedeadcode.commons.LoggerHelper
import org.objectweb.asm.ClassReader
import org.objectweb.asm.Opcodes

class AnnotationFilter : DeadCodeFilter {

    override fun filter(deadCode: DeadCode): DeadCode? {
        val reader = ClassReader(deadCode.classFile.readBytes())
        return if (reader.isAnnotation()) {
            LoggerHelper.info("[AnnotationFilter] Ignored: ${deadCode.className}")
            null
        } else {
            deadCode
        }
    }

    private fun ClassReader.isAnnotation(): Boolean = (access and Opcodes.ACC_ANNOTATION) != 0
}

package com.dipien.byebyedeadcode.code.filter

import com.dipien.byebyedeadcode.code.DeadCode
import com.dipien.byebyedeadcode.commons.LoggerHelper
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Proguard removes some static fields because the bytecode does not
 * use them, the compiler replaces its values in each place where the
 * field is used. Since we do not know which of them are not used
 * in the source code, this class filters out static fields and
 * classes with only static fields.
 */
class StaticFieldFilter : DeadCodeFilter {

    companion object {
        // e.g: 'private static final java.lang.String EXAMPLE'
        const val STATIC_FIELD = """^.+static final[^)]+$"""
    }

    override fun filter(deadCode: DeadCode): DeadCode? {
        val memberFilter = ClassMemberFilter(STATIC_FIELD.toRegex(), "StaticField")
        val filteredDeadCode = memberFilter.filter(deadCode)
        if (filteredDeadCode == null) {
            return null
        }

        val staticFieldDetector = StaticFieldDetector()
        deadCode.classReader.accept(staticFieldDetector, 0)
        if (staticFieldDetector.isClassOfConstants) {
            LoggerHelper.info("[ClassOfConstants] Ignored: ${deadCode.className}")
            return null
        }

        return filteredDeadCode
    }

    private class StaticFieldDetector : ClassVisitor(Opcodes.ASM9) {

        private var methodCount = 0
        private var nonStaticFieldCount = 0
        private var staticFieldCount = 0

        override fun visitField(access: Int, name: String?, descriptor: String?, signature: String?, value: Any?): FieldVisitor? {
            if (!isStaticField(access)) {
                nonStaticFieldCount++
            } else {
                if (!name.equals("INSTANCE")) { // Avoids ignoring singletons without constants
                    staticFieldCount++
                }
            }
            return null
        }

        override fun visitMethod(access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor? {
            if (!name.equals("<init>") && !name.equals("<clinit>")) {
                methodCount++
            }
            return null
        }

        private fun isStaticField(access: Int) = access and Opcodes.ACC_STATIC != 0

        val isClassOfConstants: Boolean
            get() = methodCount == 0 && nonStaticFieldCount == 0 && staticFieldCount > 0
    }
}

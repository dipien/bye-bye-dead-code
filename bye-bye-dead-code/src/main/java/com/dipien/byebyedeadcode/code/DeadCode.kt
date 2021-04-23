package com.dipien.byebyedeadcode.code

data class DeadCode(
    val className: String,
    val classMembers: List<String>
) {

    fun classNameToPathAnnotation(): String = className.replace(".", "/")

    /**
     * Removes nested or inner classes from name
     */
    fun rootClassNameToPathAnnotation(): String = classNameToPathAnnotation().replace("\\\$.+".toRegex(), "")
}

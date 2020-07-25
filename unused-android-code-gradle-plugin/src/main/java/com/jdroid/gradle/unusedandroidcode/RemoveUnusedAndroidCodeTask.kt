package com.jdroid.gradle.unusedandroidcode

import com.jdroid.gradle.unusedandroidcode.commons.AbstractTask

open class RemoveUnusedAndroidCodeTask : AbstractTask() {

    companion object {
        const val TASK_NAME = "removeUnusedAndroidCode"
    }

    init {
        description = "Remove unused android code"
    }

    override fun onExecute() {
        TODO()
    }
}

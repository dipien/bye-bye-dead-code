package com.dipien.byebyedeadcode.code

import com.dipien.byebyedeadcode.commons.AbstractTask

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

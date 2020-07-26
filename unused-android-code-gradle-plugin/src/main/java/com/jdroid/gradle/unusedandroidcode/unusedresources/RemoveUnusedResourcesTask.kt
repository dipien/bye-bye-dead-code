package com.jdroid.gradle.unusedandroidcode.unusedresources

import com.jdroid.gradle.unusedandroidcode.unusedresources.util.ColoredLogger
import com.jdroid.gradle.unusedandroidcode.commons.AbstractTask

open class RemoveUnusedResourcesTask : AbstractTask() {

    companion object {
        const val TASK_NAME = "removeUnusedResources"
    }

    init {
        description = "Remove unused android resources"
    }

    override fun onExecute() {
        val unusedResourcesRemoverExtension = project.extensions.findByType(UnusedResourcesRemoverExtension::class.java)!!
        logExtensionInfo(unusedResourcesRemoverExtension)
        RemoveUnusedResourcesHelper.remove(project, unusedResourcesRemoverExtension)
    }

    private fun logExtensionInfo(extension: UnusedResourcesRemoverExtension) {
        if (extension.extraRemovers.size > 0) {
            ColoredLogger.log("extraRemovers:")
            extension.extraRemovers.forEach {
                ColoredLogger.log("  $it")
            }
        }

        if (extension.excludeNames.size > 0) {
            ColoredLogger.log("excludeNames:")
            extension.excludeNames.forEach {
                ColoredLogger.log("  $it")
            }
        }

        ColoredLogger.log("dryRun: ${extension.dryRun}")
    }
}

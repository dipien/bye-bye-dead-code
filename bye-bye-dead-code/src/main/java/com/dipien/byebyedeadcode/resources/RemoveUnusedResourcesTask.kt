package com.dipien.byebyedeadcode.resources

import com.dipien.byebyedeadcode.ByeByeDeadCodeExtension
import com.dipien.byebyedeadcode.resources.util.ColoredLogger
import com.dipien.byebyedeadcode.commons.AbstractTask
import com.dipien.byebyedeadcode.resources.remover.AbstractRemover

open class RemoveUnusedResourcesTask : AbstractTask() {

    companion object {
        const val TASK_NAME = "removeUnusedResources"
    }

    init {
        description = "Remove unused android resources"
    }

    override fun onExecute() {
        val extension: ByeByeDeadCodeExtension = project.extensions.findByType(ByeByeDeadCodeExtension::class.java)!!
        logExtensionInfo(extension.dryRun, extension.unusedResourcesExcludeNames, extension.extraUnusedResourcesRemovers)
        RemoveUnusedResourcesHelper.remove(project, extension.dryRun, extension.unusedResourcesExcludeNames, extension.extraUnusedResourcesRemovers)
    }

    private fun logExtensionInfo(dryRun: Boolean, excludeNames: List<String>, extraRemovers: List<AbstractRemover>) {
        if (extraRemovers.isNotEmpty()) {
            ColoredLogger.log("extraRemovers:")
            extraRemovers.forEach {
                ColoredLogger.log("  $it")
            }
        }

        if (excludeNames.isNotEmpty()) {
            ColoredLogger.log("excludeNames:")
            excludeNames.forEach {
                ColoredLogger.log("  $it")
            }
        }

        ColoredLogger.log("dryRun: $dryRun")
    }
}

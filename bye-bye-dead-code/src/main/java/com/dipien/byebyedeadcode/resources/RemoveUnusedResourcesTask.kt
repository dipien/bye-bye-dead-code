package com.dipien.byebyedeadcode.resources

import com.dipien.byebyedeadcode.resources.util.ColoredLogger
import com.dipien.byebyedeadcode.commons.AbstractTask
import com.dipien.byebyedeadcode.resources.remover.AbstractRemover
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional

open class RemoveUnusedResourcesTask : AbstractTask() {

    companion object {
        const val TASK_NAME = "removeUnusedResources"
    }

    init {
        description = "Remove unused android resources"
    }

    @get:Input
    @get:Optional
    var unusedResourcesExcludeNames: List<String> = emptyList()

    @get:Input
    @get:Optional
    var extraUnusedResourcesRemovers: List<AbstractRemover> = emptyList()

    override fun onExecute() {
        if (unusedResourcesExcludeNames.isNotEmpty()) {
            ColoredLogger.log("extraRemovers:")
            unusedResourcesExcludeNames.forEach {
                ColoredLogger.log("  $it")
            }
        }

        if (unusedResourcesExcludeNames.isNotEmpty()) {
            ColoredLogger.log("excludeNames:")
            unusedResourcesExcludeNames.forEach {
                ColoredLogger.log("  $it")
            }
        }

        ColoredLogger.log("dryRun: $dryRun")

        RemoveUnusedResourcesHelper.remove(project, dryRun, unusedResourcesExcludeNames, extraUnusedResourcesRemovers)
    }
}

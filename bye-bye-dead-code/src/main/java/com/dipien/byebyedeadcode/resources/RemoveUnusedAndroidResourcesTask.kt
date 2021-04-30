package com.dipien.byebyedeadcode.resources

import com.dipien.byebyedeadcode.resources.util.ColoredLogger
import com.dipien.byebyedeadcode.commons.AbstractTask
import com.dipien.byebyedeadcode.resources.remover.AbstractRemover
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional

open class RemoveUnusedAndroidResourcesTask : AbstractTask() {

    companion object {
        const val TASK_NAME = "removeUnusedAndroidResources"
    }

    init {
        description = "Remove unused android resources"
    }

    @get:Input
    @get:Optional
    var unusedResourcesExcludeNames: List<String> = emptyList()

    @get:Input
    @get:Optional
    var unusedResourcesExtraRemovers: List<AbstractRemover> = emptyList()

    override fun onExecute() {

        // TODO Fail if an android project is not found

        if (unusedResourcesExtraRemovers.isNotEmpty()) {
            ColoredLogger.log("androidUnusedResourcesExtraRemovers:")
            unusedResourcesExtraRemovers.forEach {
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

        RemoveUnusedResourcesHelper.remove(project, dryRun, unusedResourcesExcludeNames, unusedResourcesExtraRemovers)
    }
}

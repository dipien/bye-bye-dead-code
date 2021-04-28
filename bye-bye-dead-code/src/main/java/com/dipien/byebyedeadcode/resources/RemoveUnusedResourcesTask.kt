package com.dipien.byebyedeadcode.resources

import com.dipien.byebyedeadcode.resources.util.ColoredLogger
import com.dipien.byebyedeadcode.commons.AbstractTask
import com.dipien.byebyedeadcode.resources.remover.AbstractRemover
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional

open class RemoveUnusedResourcesTask : AbstractTask() {

    companion object {
        const val TASK_NAME = "removeUnusedAndroidResources"
    }

    init {
        description = "Remove unused android resources"
    }

    @get:Input
    @get:Optional
    var androidUnusedResourcesExcludeNames: List<String> = emptyList()

    @get:Input
    @get:Optional
    var androidUnusedResourcesExtraRemovers: List<AbstractRemover> = emptyList()

    override fun onExecute() {

        // TODO Fail if an android project is not found

        if (androidUnusedResourcesExtraRemovers.isNotEmpty()) {
            ColoredLogger.log("androidUnusedResourcesExtraRemovers:")
            androidUnusedResourcesExtraRemovers.forEach {
                ColoredLogger.log("  $it")
            }
        }

        if (androidUnusedResourcesExcludeNames.isNotEmpty()) {
            ColoredLogger.log("excludeNames:")
            androidUnusedResourcesExcludeNames.forEach {
                ColoredLogger.log("  $it")
            }
        }

        ColoredLogger.log("dryRun: $dryRun")

        RemoveUnusedResourcesHelper.remove(project, dryRun, androidUnusedResourcesExcludeNames, androidUnusedResourcesExtraRemovers)
    }
}

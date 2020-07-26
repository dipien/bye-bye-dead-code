package com.jdroid.gradle.unusedandroidcode.unusedresources

import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.filetype.AnimFileRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.filetype.AnimatorFileRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.filetype.ColorFileRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.filetype.DrawableFileRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.filetype.LayoutFileRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.filetype.MenuFileRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.filetype.MipmapFileRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.valuetype.AttrXmlValueRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.valuetype.BoolXmlValueRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.valuetype.ColorXmlValueRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.valuetype.DimenXmlValueRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.valuetype.IdXmlValueRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.valuetype.IntegerXmlValueRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.valuetype.StringXmlValueRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.valuetype.StyleXmlValueRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.remover.valuetype.ThemeXmlValueRemover
import com.jdroid.gradle.unusedandroidcode.unusedresources.util.ResultsReport
import org.gradle.api.Project

object RemoveUnusedResourcesHelper {

    fun remove(project: Project, extension: UnusedResourcesRemoverExtension) {

        ResultsReport.clearResults()

        removeInternal(project, extension)

        // TODO We call it again to find more unused resources. Improve this
        if (ResultsReport.getResults().isNotEmpty()) {
            removeInternal(project, extension)
        }
    }

    private fun removeInternal(project: Project, extension: UnusedResourcesRemoverExtension) {
        // Remove unused files
        listOf(
                LayoutFileRemover(),
                MenuFileRemover(),
                MipmapFileRemover(),
                DrawableFileRemover(),
                AnimatorFileRemover(),
                AnimFileRemover(),
                ColorFileRemover()
        ).forEach {
            it.remove(project, extension)
        }

        // Remove unused xml values
        listOf(
                ThemeXmlValueRemover(),
                StyleXmlValueRemover(),
                StringXmlValueRemover(),
                DimenXmlValueRemover(),
                ColorXmlValueRemover(),
                IntegerXmlValueRemover(),
                BoolXmlValueRemover(),
                IdXmlValueRemover(),
                AttrXmlValueRemover()
        ).forEach {
            it.remove(project, extension)
        }

        // Remove files or xml values in extra setting
        extension.extraRemovers.forEach {
            it.remove(project, extension)
        }
    }
}

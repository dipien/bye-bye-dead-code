package com.jdroid.gradle.unusedandroidcode.unusedresources

import com.github.konifar.gradle.remover.UnusedResourcesRemoverExtension
import com.github.konifar.gradle.remover.remover.filetype.AnimFileRemover
import com.github.konifar.gradle.remover.remover.filetype.AnimatorFileRemover
import com.github.konifar.gradle.remover.remover.filetype.ColorFileRemover
import com.github.konifar.gradle.remover.remover.filetype.DrawableFileRemover
import com.github.konifar.gradle.remover.remover.filetype.LayoutFileRemover
import com.github.konifar.gradle.remover.remover.filetype.MenuFileRemover
import com.github.konifar.gradle.remover.remover.filetype.MipmapFileRemover
import com.github.konifar.gradle.remover.remover.valuetype.AttrXmlValueRemover
import com.github.konifar.gradle.remover.remover.valuetype.BoolXmlValueRemover
import com.github.konifar.gradle.remover.remover.valuetype.ColorXmlValueRemover
import com.github.konifar.gradle.remover.remover.valuetype.DimenXmlValueRemover
import com.github.konifar.gradle.remover.remover.valuetype.IdXmlValueRemover
import com.github.konifar.gradle.remover.remover.valuetype.IntegerXmlValueRemover
import com.github.konifar.gradle.remover.remover.valuetype.StringXmlValueRemover
import com.github.konifar.gradle.remover.remover.valuetype.StyleXmlValueRemover
import com.github.konifar.gradle.remover.remover.valuetype.ThemeXmlValueRemover
import com.github.konifar.gradle.remover.util.ResultsReport
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

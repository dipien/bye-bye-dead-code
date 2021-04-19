package com.dipien.unusedandroidcode.resources

import com.dipien.unusedandroidcode.resources.remover.filetype.AnimFileRemover
import com.dipien.unusedandroidcode.resources.remover.filetype.AnimatorFileRemover
import com.dipien.unusedandroidcode.resources.remover.filetype.ColorFileRemover
import com.dipien.unusedandroidcode.resources.remover.filetype.DrawableFileRemover
import com.dipien.unusedandroidcode.resources.remover.filetype.LayoutFileRemover
import com.dipien.unusedandroidcode.resources.remover.filetype.MenuFileRemover
import com.dipien.unusedandroidcode.resources.remover.filetype.MipmapFileRemover
import com.dipien.unusedandroidcode.resources.remover.valuetype.AttrXmlValueRemover
import com.dipien.unusedandroidcode.resources.remover.valuetype.BoolXmlValueRemover
import com.dipien.unusedandroidcode.resources.remover.valuetype.ColorXmlValueRemover
import com.dipien.unusedandroidcode.resources.remover.valuetype.DimenXmlValueRemover
import com.dipien.unusedandroidcode.resources.remover.valuetype.IdXmlValueRemover
import com.dipien.unusedandroidcode.resources.remover.valuetype.IntegerXmlValueRemover
import com.dipien.unusedandroidcode.resources.remover.valuetype.StringXmlValueRemover
import com.dipien.unusedandroidcode.resources.remover.valuetype.StyleXmlValueRemover
import com.dipien.unusedandroidcode.resources.remover.valuetype.ThemeXmlValueRemover
import com.dipien.unusedandroidcode.resources.util.ResultsReport
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

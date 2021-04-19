package com.dipien.byebyedeadcode.resources

import com.dipien.byebyedeadcode.resources.remover.filetype.AnimFileRemover
import com.dipien.byebyedeadcode.resources.remover.filetype.AnimatorFileRemover
import com.dipien.byebyedeadcode.resources.remover.filetype.ColorFileRemover
import com.dipien.byebyedeadcode.resources.remover.filetype.DrawableFileRemover
import com.dipien.byebyedeadcode.resources.remover.filetype.LayoutFileRemover
import com.dipien.byebyedeadcode.resources.remover.filetype.MenuFileRemover
import com.dipien.byebyedeadcode.resources.remover.filetype.MipmapFileRemover
import com.dipien.byebyedeadcode.resources.remover.valuetype.AttrXmlValueRemover
import com.dipien.byebyedeadcode.resources.remover.valuetype.BoolXmlValueRemover
import com.dipien.byebyedeadcode.resources.remover.valuetype.ColorXmlValueRemover
import com.dipien.byebyedeadcode.resources.remover.valuetype.DimenXmlValueRemover
import com.dipien.byebyedeadcode.resources.remover.valuetype.IdXmlValueRemover
import com.dipien.byebyedeadcode.resources.remover.valuetype.IntegerXmlValueRemover
import com.dipien.byebyedeadcode.resources.remover.valuetype.StringXmlValueRemover
import com.dipien.byebyedeadcode.resources.remover.valuetype.StyleXmlValueRemover
import com.dipien.byebyedeadcode.resources.remover.valuetype.ThemeXmlValueRemover
import com.dipien.byebyedeadcode.resources.util.ResultsReport
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

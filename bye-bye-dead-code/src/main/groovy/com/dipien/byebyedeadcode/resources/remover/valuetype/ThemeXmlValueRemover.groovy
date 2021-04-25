package com.dipien.byebyedeadcode.resources.remover.valuetype

import com.dipien.byebyedeadcode.resources.remover.SearchPattern


class ThemeXmlValueRemover extends XmlValueRemover {

    ThemeXmlValueRemover() {
        // theme is actually style
        super("theme", "style", "style", SearchPattern.Type.STYLE)
    }

}
package com.dipien.unusedandroidcode.resources.remover.valuetype


class ThemeXmlValueRemover extends XmlValueRemover {

    ThemeXmlValueRemover() {
        // theme is actually style
        super("theme", "style", "style", SearchPattern.Type.STYLE)
    }

}
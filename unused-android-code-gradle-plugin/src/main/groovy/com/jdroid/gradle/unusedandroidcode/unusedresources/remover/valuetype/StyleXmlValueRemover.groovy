package com.jdroid.gradle.unusedandroidcode.unusedresources.remover.valuetype


class StyleXmlValueRemover extends XmlValueRemover {

    StyleXmlValueRemover() {
        super("style", "style", "style", SearchPattern.Type.STYLE)
    }

}
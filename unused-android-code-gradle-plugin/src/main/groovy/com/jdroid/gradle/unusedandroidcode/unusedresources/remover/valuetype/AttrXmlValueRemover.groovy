package com.jdroid.gradle.unusedandroidcode.unusedresources.remover.valuetype

class AttrXmlValueRemover extends XmlValueRemover {

    AttrXmlValueRemover() {
        super("attr", "styleable", "declare-styleable")
    }

}
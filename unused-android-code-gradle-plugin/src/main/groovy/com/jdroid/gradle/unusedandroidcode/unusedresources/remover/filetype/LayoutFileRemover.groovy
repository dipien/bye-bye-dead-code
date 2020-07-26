package com.jdroid.gradle.unusedandroidcode.unusedresources.remover.filetype


class LayoutFileRemover extends FileRemover {

    LayoutFileRemover() {
        super("layout", "layout", SearchPattern.Type.LAYOUT)
    }

}
package com.jdroid.gradle.unusedandroidcode.unusedresources.remover.filetype


class DrawableFileRemover extends FileRemover {

    DrawableFileRemover() {
        super("drawable", "drawable", SearchPattern.Type.DRAWABLE)
    }

}
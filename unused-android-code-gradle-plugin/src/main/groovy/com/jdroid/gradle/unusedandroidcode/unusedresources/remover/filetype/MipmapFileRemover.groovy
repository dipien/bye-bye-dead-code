package com.jdroid.gradle.unusedandroidcode.unusedresources.remover.filetype


class MipmapFileRemover extends FileRemover {

    MipmapFileRemover() {
        super("mipmap", "mipmap", SearchPattern.Type.DRAWABLE)
    }

}
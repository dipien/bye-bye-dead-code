package com.dipien.byebyedeadcode.resources.remover.filetype


class DrawableFileRemover extends FileRemover {

    DrawableFileRemover() {
        super("drawable", "drawable", SearchPattern.Type.DRAWABLE)
    }

}
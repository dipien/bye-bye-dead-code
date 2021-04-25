package com.dipien.byebyedeadcode.resources.remover.filetype

import com.dipien.byebyedeadcode.resources.remover.SearchPattern

class DrawableFileRemover extends FileRemover {

    DrawableFileRemover() {
        super("drawable", "drawable", SearchPattern.Type.DRAWABLE)
    }

}
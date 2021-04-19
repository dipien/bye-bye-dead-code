package com.dipien.unusedandroidcode.resources.remover.filetype


class LayoutFileRemover extends FileRemover {

    LayoutFileRemover() {
        super("layout", "layout", SearchPattern.Type.LAYOUT)
    }

}
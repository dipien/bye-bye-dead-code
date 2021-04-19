package com.dipien.byebyedeadcode.resources.remover.filetype


class LayoutFileRemover extends FileRemover {

    LayoutFileRemover() {
        super("layout", "layout", SearchPattern.Type.LAYOUT)
    }

}
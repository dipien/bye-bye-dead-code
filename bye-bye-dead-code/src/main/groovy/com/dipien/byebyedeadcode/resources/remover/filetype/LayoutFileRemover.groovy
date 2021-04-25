package com.dipien.byebyedeadcode.resources.remover.filetype

import com.dipien.byebyedeadcode.resources.remover.SearchPattern

class LayoutFileRemover extends FileRemover {

    LayoutFileRemover() {
        super("layout", "layout", SearchPattern.Type.LAYOUT)
    }

}
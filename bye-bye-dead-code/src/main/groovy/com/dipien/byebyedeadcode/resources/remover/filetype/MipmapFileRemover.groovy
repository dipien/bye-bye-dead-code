package com.dipien.byebyedeadcode.resources.remover.filetype

import com.dipien.byebyedeadcode.resources.remover.SearchPattern

class MipmapFileRemover extends FileRemover {

    MipmapFileRemover() {
        super("mipmap", "mipmap", SearchPattern.Type.DRAWABLE)
    }

}
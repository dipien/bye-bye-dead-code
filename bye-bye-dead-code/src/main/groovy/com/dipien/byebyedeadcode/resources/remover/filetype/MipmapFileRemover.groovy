package com.dipien.byebyedeadcode.resources.remover.filetype


class MipmapFileRemover extends FileRemover {

    MipmapFileRemover() {
        super("mipmap", "mipmap", SearchPattern.Type.DRAWABLE)
    }

}
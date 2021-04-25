package com.dipien.byebyedeadcode.resources.remover.filetype

import com.dipien.byebyedeadcode.resources.remover.SearchPattern

class MipmapFileRemover : FileRemover("mipmap", "mipmap", SearchPattern.Type.DRAWABLE)
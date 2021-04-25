package com.dipien.byebyedeadcode.resources.remover.valuetype

import com.dipien.byebyedeadcode.resources.remover.SearchPattern


class StyleXmlValueRemover extends XmlValueRemover {

    StyleXmlValueRemover() {
        super("style", "style", "style", SearchPattern.Type.STYLE)
    }

}
package com.dipien.byebyedeadcode.resources.remover.valuetype

import com.dipien.byebyedeadcode.resources.remover.SearchPattern

class ThemeXmlValueRemover : XmlValueRemover("theme", "style", "style", SearchPattern.Type.STYLE)

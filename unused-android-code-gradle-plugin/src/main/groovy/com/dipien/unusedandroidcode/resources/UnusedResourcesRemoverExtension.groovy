package com.dipien.unusedandroidcode.resources

class UnusedResourcesRemoverExtension {

    public static final String NAME = "unusedResourcesRemover"

    List<com.dipien.unusedandroidcode.resources.remover.AbstractRemover> extraRemovers = []

    List<String> excludeNames = []

    boolean dryRun = false

    com.dipien.unusedandroidcode.resources.remover.AbstractRemover createFileRemover(String fileType, String resourceName, String type = null) {
        return new com.dipien.unusedandroidcode.resources.remover.filetype.FileRemover(fileType, resourceName, SearchPattern.Type.from(type))
    }

    /**
     * @param fileType is a prefix of the file name like `font`, `text_appearance`
     * @param resourceName is a name to check its existence like @`string`/app_name, $.`string`/app_name
     * @param tagName is a name to extract value from xml like <`string` name="app_name">, <`dimen` name="width">
     * @param type is search regex pattern type. Ex) themes.xml should specified to Type.STYLE because it is almost same usage with styles.xml
     * @return
     */
    com.dipien.unusedandroidcode.resources.remover.AbstractRemover createXmlValueRemover(String fileType, String resourceName, String tagName, String type = null) {
        return new com.dipien.unusedandroidcode.resources.remover.valuetype.XmlValueRemover(fileType, resourceName, tagName, SearchPattern.Type.from(type))
    }

}
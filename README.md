# Unused Android Sources

Gradle Plugin that removes unused resources in Android projects.

## Features
- Easy to remove unused resources by simple gradle command
- Customize extra/exclude files configuration
- Support DataBinding & Multi module project

## Setup

Add the following configuration to your root `build.gradle`, replacing X.Y.Z by the [latest version](https://github.com/maxirosson/unused-android-code-gradle-plugin/releases/latest)

Using the plugins DSL:

```groovy
plugins {
  id "com.dipien:unused-android-code-gradle-plugin" version "X.Y.Z"
}
```

Using legacy plugin application:

```groovy
buildscript {
    repositories {
        mavenCentral() // or gradlePluginPortal()
    }
    dependencies {
        classpath("com.dipien.unused-android-code:X.Y.Z")
    }
}
    
apply plugin: "com.github.konifar.gradle.unused-resources-remover"
```

### Run

```shell
$ ./gradlew removeUnusedResources
```

# Advanced usage

This plugin checks some basic resource files below.

```shell
|--res
   |--anim
   |  |--*.xml
   |--animator
   |  |--*.xml
   |--drawable*
   |  |--*.xml
   |  |--*.png
   |  |--*.jpg
   |  |--*.9.png // 9-patch
   |--layout*
   |  |--*.xml
   |--menu
   |  |--*.xml
   |--mipmap*
   |  |--*.xml
   |  |--*.png
   |--values*
      |--attrs*.xml
      |--bools*.xml
      |--colors*.xml
      |--dimens*.xml
      |--floats*.xml
      |--ids*.xml
      |--integers*.xml
      |--strings*.xml
      |--styles*.xml
      |--themes*.xml
```

If you want to check other files, you can add custom remover settings in `build.gradle`.

Here is two example.

- `fonts.xml` (actually same format with strings.xml)
- `text_appearance.xml` (actually same format with styles.xml)

```gradle
unusedResourcesRemover { 
  extraRemovers = [
    createXmlValueRemover("fonts", "string", "string"), // fonts.xml
    createXmlValueRemover("text_appearance", "style", "style", "style") // text_appearance.xml
  ]
  ...
}
```

There are other optional configuration below

```gradle
unusedResourcesRemover {
  ...
  // Write file or directory names
  excludeNames = [
    "strings.xml", // strings.xml is never checked
    "res/drawable" // drawable* dirs are never checked
  ]
  
  // When dryRun option is true, unused files are not removed.
  dryRun = true // default false
}
```

# Copyright

Part of the source code of this project is based on
https://github.com/konifar/gradle-unused-resources-remover-plugin
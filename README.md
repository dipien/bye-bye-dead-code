[![Dipien](https://raw.githubusercontent.com/dipien/dipien-component-builder/master/.github/dipien_logo.png)](http://www.dipien.com)

# Bye Bye Dead Code

Gradle Plugin to automatically detect unused code on your Kotlin or Android project

## Features

// TODO

## Setup

Add the following configuration to your root `build.gradle`, replacing X.Y.Z with the [latest version](https://github.com/dipien/bye-bye-dead-code/releases/latest)

```groovy
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.dipien:bye-bye-dead-code:X.Y.Z")
    }
}

apply plugin: "com.dipien.bye-bye-dead-code"
```

## Usage

// TODO

### Tasks

#### Generate unused code report 

This task depends on R8 which detects unused code when you build your project. So you must enable `minifyEnabled` in the `build.gradle` of your app module. [See how to configure R8](https://developer.android.com/studio/build/shrink-code)

```
./gradlew app:bundleRelease generateDeadCodeReport
```

The report `app/build/outputs/dead_code/usage.txt` will be generated.

##### Extension properties

The extension is configured by default to work using the release build type, but you can change this if you want. Using the extension `byeByeDeadCode` you can modify the following properties:

Name | Type | Description
--- | --- | ---
ignoredClasses | List | *Optional.* List of ignored classes. Supports regex.
ignoredMembers | List | *Optional.* List of ignored class members. Supports regex.
generatedClassesDirs | List | *Required.* Directories that are used by different tools (e.g. kapt) to store generated source code. <br/><br/> Default: <br/>["build/generated/source/kapt/release", <br/>"build/generated/source/navigation-args/release"]
compiledKotlinClassesDir | String | *Required.* Directory where compiled kotlin classes are stored. <br/><br/> Default: "build/tmp/kotlin-classes/release"
compiledJavaClassesDir | String | *Required.* Directory where compiled Java classes are stored. <br/><br/> Default: "build/intermediates/javac/release"
r8UsageFilePath | String | *Required.* File path of usage.txt generated by R8. <br/><br/> Default: "app/build/outputs/mapping/release/usage.txt"
reportFilePath | String | *Required.* File path to store the report. <br/><br/> Default: "app/build/outputs/dead_code/usage.txt"

#### Remove unused Android resources

Remove unused Android resources

```
./gradlew removeUnusedAndroidResources
```

## Copyright

Part of the source code of this project is based on [Unused Resources Remover for Android](https://github.com/konifar/gradle-unused-resources-remover-plugin)

## Versioning

This project uses the [Semantic Versioning guidelines](http://semver.org/) for transparency into our release cycle.

## Sponsor this project

Sponsor this open source project to help us get the funding we need to continue working on it.

* [Donate with Bitcoin Lightning](http://alby.dipien.com)
* [Donate cryptocurrency](http://coinbase.dipien.com/)
* [Donate with PayPal](http://paypal.dipien.com/)
* [Donate on Patreon](http://patreon.dipien.com/)
* [Become a member of Medium](https://maxirosson.medium.com/membership) [We will receive a portion of your membership fee]

## Follow us
* [Twitter](http://twitter.dipien.com)
* [Medium](http://medium.dipien.com)
* [Instagram](http://instagram.dipien.com)
* [Pinterest](http://pinterest.dipien.com)
* [GitHub](http://github.dipien.com)

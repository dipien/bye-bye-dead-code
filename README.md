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

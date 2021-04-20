[![Dipien](https://raw.githubusercontent.com/dipien/dipien-component-builder/master/.github/dipien_logo.png)](http://www.dipien.com)

# Bye Bye Dead Code

Gradle Plugin to automatically detect unused code on your Kotlin or Android project

## Features
- Easy to remove unused resources by simple gradle command
- Customize extra/exclude files configuration
- Support DataBinding & Multi module project

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

## Copyright

Part of the source code of this project is based on [Unused Resources Remover for Android](https://github.com/konifar/gradle-unused-resources-remover-plugin)

## Versioning

This project uses the [Semantic Versioning guidelines](http://semver.org/) for transparency into our release cycle.

## Donations

Donations are greatly appreciated. You can help us to pay for our domain and this project development.

* [Donate cryptocurrency](http://coinbase.dipien.com/)
* [Donate with PayPal](http://paypal.dipien.com/)
* [Donate on Patreon](http://patreon.dipien.com/)

## Follow us
* [Twitter](http://twitter.dipien.com)
* [Medium](http://medium.dipien.com)
* [Instagram](http://instagram.dipien.com)
* [Pinterest](http://pinterest.dipien.com)
* [GitHub](http://github.dipien.com)
* [Blog](http://blog.dipien.com)
plugins {
    plugin(Config.Dependencies.Plugins.androidLibrary)
    plugin(Config.Dependencies.Plugins.kotlinMultiplatform)
    plugin(Config.Dependencies.Plugins.kotlinAndroidExtensions)
}

kotlin {
    android()
    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Config.Dependencies.Shared.Concurrency.coroutinesCore)
                implementation("com.badoo.reaktive:reaktive:1.1.22")
                implementation("com.badoo.reaktive:reaktive-annotations:1.1.22")
                implementation("com.badoo.reaktive:coroutines-interop:1.1.22")
            }
        }
    }
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}
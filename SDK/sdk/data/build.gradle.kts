plugins {
    plugin(Config.Dependencies.Plugins.androidLibrary)
    plugin(Config.Dependencies.Plugins.kotlinMultiplatform)
    plugin(Config.Dependencies.Plugins.kotlinAndroidExtensions)
    plugin(Config.Dependencies.Plugins.kotlinSerialization)
    plugin(Config.Dependencies.Plugins.databasePlugin)
}
kotlin {
    android()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Config.Dependencies.Shared.Concurrency.coroutinesCore)

                implementation(Config.Dependencies.Shared.Serialization.kotlinSerialization)
                implementation(Config.Dependencies.Shared.Serialization.kotlinSerializationJson)

                implementation(Config.Dependencies.Shared.Network.ktorCommonClientCore)
                implementation(Config.Dependencies.Shared.Network.ktorCommonClientLogging)
                implementation(Config.Dependencies.Shared.Network.ktorCommonClientSerialization)

                implementation(project(":sdk:domain"))

                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")
                implementation("com.squareup.sqldelight:runtime:1.4.4")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Config.Dependencies.Shared.Concurrency.coroutinesAndroid)
                implementation(Config.Dependencies.Shared.Network.ktorAndroidClientCore)

                implementation("io.ktor:ktor-client-okhttp:1.5.0")
                implementation("com.squareup.okhttp3:okhttp:3.12.1")
                implementation("com.squareup.sqldelight:android-driver:1.4.4")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Config.Dependencies.Shared.Network.ktorIosClientCore)
                implementation("com.squareup.sqldelight:native-driver:1.4.4")
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

sqldelight {
    database("SdkCacheDatabase") {
        packageName = "cache"
    }
}
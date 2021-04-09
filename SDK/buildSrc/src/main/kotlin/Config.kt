object Config {

    const val kotlinVersion = "1.4.30"

    object Dependencies {
        object Shared {
            const val coroutinesCoreVersion = "1.4.2-native-mt"
            private const val coroutinesAndroidVersion = "1.4.2"

            private const val kotlinxSerializationVersion = "1.1.0"
            private const val ktorClientVersion = "1.5.1"

            object Concurrency {
                const val coroutinesCore =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesCoreVersion"
                const val coroutinesAndroid =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesAndroidVersion"
            }

            object Network {
                const val ktorCommonClientCore = "io.ktor:ktor-client-core:$ktorClientVersion"
                const val ktorCommonClientJson = "io.ktor:ktor-client-json:$ktorClientVersion"
                const val ktorCommonClientLogging = "io.ktor:ktor-client-logging:$ktorClientVersion"
                const val ktorAndroidClientCore = "io.ktor:ktor-client-okhttp:$ktorClientVersion"
                const val ktorIosClientCore = "io.ktor:ktor-client-ios:$ktorClientVersion"
                const val ktorCommonClientSerialization =
                    "io.ktor:ktor-client-serialization:$ktorClientVersion"
            }

            object Serialization {
                const val kotlinSerialization =
                    "org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion"
                const val kotlinSerializationJson =
                    "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion"
            }
        }

        object Android {

        }

        object IOS {

        }

        object Plugins {
            val androidLibrary = GradlePlugin(id = "com.android.library")
            val kotlinMultiplatform = GradlePlugin(
                id = "org.jetbrains.kotlin.multiplatform",
                module = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
            )
            val kotlinAndroidExtensions = GradlePlugin(id = "kotlin-android-extensions")
            val kotlinSerialization = GradlePlugin(
                id = "org.jetbrains.kotlin.plugin.serialization",
                module = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
            )
            val databasePlugin = GradlePlugin(id = "com.squareup.sqldelight")
        }
    }

    object Android {
        const val compileSdkVersion = 30
        const val targetSdkVersion = 30
        const val minSdkVersion = 16
    }

    object SDK {
        object Modules {
            val data = MultiPlatformModule(
                name = ":sdk:data",
                exported = true
            )

            val domain = MultiPlatformModule(
                name = ":sdk:domain",
                exported = true
            )

            val coreModules = listOf(data, domain)
        }
    }

    private val String.mpl: MultiPlatformLibrary
        get() = MultiPlatformLibrary(
            common = this,
            iosX64 = this.replace(Regex("(.*):(.*):(.*)"), "$1:$2-iosx64:$3"),
            iosArm64 = this.replace(Regex("(.*):(.*):(.*)"), "$1:$2-iosarm64:$3")
        )
}
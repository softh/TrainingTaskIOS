plugins {
    plugin(Config.Dependencies.Plugins.kotlinMultiplatform)
}

kotlin {
    jvm()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Config.Dependencies.Shared.Concurrency.coroutinesCore)
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")
                implementation("com.badoo.reaktive:reaktive:1.1.22")
                implementation("com.badoo.reaktive:reaktive-annotations:1.1.22")
                implementation("com.badoo.reaktive:coroutines-interop:1.1.22-nmtc")
            }
        }
    }
}
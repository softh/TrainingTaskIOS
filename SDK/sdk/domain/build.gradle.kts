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
            }
        }
    }
}
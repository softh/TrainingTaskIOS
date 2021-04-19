val libraryName = "CryptoCurrencySDK"
val libraryVersion = "1.0.1"

plugins {
    plugin(Config.Dependencies.Plugins.androidLibrary)
    plugin(Config.Dependencies.Plugins.kotlinMultiplatform)
    plugin(Config.Dependencies.Plugins.kotlinAndroidExtensions)
}

kotlin {
    android()
    ios {
        binaries.framework {
            baseName = libraryName
            version = libraryVersion
            export(project(":sdk:domain"))
            export(project(":sdk:data"))
            linkerOpts.add("-lsqlite3")
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Config.Dependencies.Shared.Concurrency.coroutinesCore)
                implementation("com.badoo.reaktive:reaktive:1.1.22")
                implementation("com.badoo.reaktive:reaktive-annotations:1.1.22")
                implementation("com.badoo.reaktive:coroutines-interop:1.1.22-nmtc")
                api(project(":sdk:domain"))
                api(project(":sdk:data"))
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

val buildXCFramework by tasks.creating(Exec::class) {
    group = "artifact"
    executable = "sh"
    args = listOf(
        "${rootProject.projectDir.resolve("scripts/buildXCFramework.sh")}",
        "${buildDir.resolve("bin/iosArm64/releaseFramework/$libraryName.framework")}",
        "${buildDir.resolve("bin/iosX64/releaseFramework/$libraryName.framework")}",
        "${rootProject.buildDir}/$libraryName.xcframework"
    )
}

tasks.getByName("buildXCFramework")
    .dependsOn("linkReleaseFrameworkIosArm64")
    .dependsOn("linkReleaseFrameworkIosX64")
    .dependsOn("clean")
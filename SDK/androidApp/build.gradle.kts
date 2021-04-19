plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.2")
    implementation(project(":sdk:data"))
    implementation(project(":sdk:domain"))
    implementation(project(":sdk:facade"))
    implementation(Config.Dependencies.Shared.Concurrency.coroutinesCore)
    implementation (Config.Dependencies.Shared.Concurrency.coroutinesAndroid)
    implementation ("com.badoo.reaktive:reaktive-android:1.1.22")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "by.st.kmm.currencies.androidApp"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
}
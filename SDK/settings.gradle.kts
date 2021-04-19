pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "CryptoCurrenciesSDK"


include(":androidApp")
include(":sdk")
include(":sdk:data")
include(":sdk:domain")
include(":sdk:tools")
include(":sdk:facade")


pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "CryptoCurrenciesApp"


include(":androidApp")
include(":sdk")
include(":sdk:data")
include(":sdk:domain")
include(":sdk:facade")


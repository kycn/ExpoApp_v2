enableFeaturePreview("VERSION_CATALOGS")
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../ExpoApp_v2/gradle/libraries.versions.toml"))
        }
    }
}
rootProject.name = "ExpoApp_v2"
include(":app")

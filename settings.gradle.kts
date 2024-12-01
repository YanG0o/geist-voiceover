pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "gest-voiceover"
include(":app")
include(":modulesBase:http")
include(":modulesBase:base-view")
include(":modulesBase:commonutil")
//include(":https")
include(":modulesFeat:login")
include(":modulesFeat:module-res")

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
        maven {
            credentials {
                username = "VishalChitrode"
                password = "123@Vishal"
            }
            url = uri("https://repositories.tomtom.com/artifactory/maven")

        }

    }
}

rootProject.name = "Google Maps Using the TomTom Nav SDK"
include(":app")

pluginManagement {
    repositories {
        maven {
            url = uri(
                "https://maven.myket.ir")
        }
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url = uri(
                "https://maven.myket.ir")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "PlantsApp"
include(":app")
 
pluginManagement {
    repositories {
        google()
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

rootProject.name = "DecomposeTest"
include(":app")
include(":feature:cards:api")
include(":feature:cards:impl")
include(":feature:details:api")
include(":feature:details:impl")
include(":common")

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
include(":common")
include(":feature:splash:api")
include(":feature:splash:impl")
include(":feature:cards:api")
include(":feature:cards:impl")
include(":feature:details:api")
include(":feature:details:impl")
include(":feature:dashboard:api")
include(":feature:dashboard:impl")
include(":feature:tab1:api")
include(":feature:tab1:impl")
include(":feature:tab2:api")
include(":feature:tab2:impl")

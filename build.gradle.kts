import org.gradle.api.tasks.wrapper.Wrapper

plugins {
    alias(libs.plugins.jarTest) apply false
    alias(libs.plugins.docker) apply false
    alias(libs.plugins.shadow) apply false
    alias(libs.plugins.scalatest) apply false
    alias(libs.plugins.git)
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management) apply false
    alias(libs.plugins.node.gradle) apply false

    id("idea")
}

description = "Content Manage System"
group = "com.rainmore.cms"
version "2.0.0"

defaultTasks "clean"

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

val projectLibDir = rootDir.toPath().resolve("project")

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.BIN
    gradleVersion = project.properties["gradleVersion"] as String
}

tasks.register("tagVersion") {
    group = "Publishing"
    description = "Tags the current head with the project's version."
    doLast {
        val grgit: org.ajoberstar.grgit.Grgit by project
        val version: String by project
        grgit.tag.add {
            name = version
            message = "Release of v${version}"
        }

        logger.lifecycle("A new version {} has been tagged.", version)
    }
}
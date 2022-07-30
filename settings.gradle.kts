rootProject.name = 'cms'
include 'api-elements', 'api', 'ui'

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        id 'org.springframework.boot' version '2.7.2'
        id 'com.github.hauner.jarTest' version '1.0.1'
        id 'com.github.maiflai.scalatest' version '0.32'
        id 'com.bmuschko.docker-spring-boot-application' version '7.4.0'
        id 'org.ajoberstar.grgit' version '4.1.1'
        id 'com.github.node-gradle.node' version '3.4.0'
    }
}




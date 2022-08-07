plugins {
    alias(libs.plugins.scalatest)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)

    id("idea")
    id("application")
}

description = "API"
group = rootProject.group
version = rootProject.version

application {
    mainClass.set(listOf(project.group.toString(), "Application").joinToString("."))
}

configurations.all {
    exclude("org.apache.logging.log4j:*")
}

project.ext.set("imageName", "edi-com-2")

val rootProjectLibDir = rootDir.toPath().resolve("project")
val projectLibDir     = projectDir.toPath().resolve("project")

apply(from = rootProjectLibDir.resolve("utils.gradle"))
apply(from = rootProjectLibDir.resolve("assemble.gradle"))
apply(from = rootProjectLibDir.resolve("scala.gradle"))
apply(from = rootProjectLibDir.resolve("spring.gradle"))
apply(from = rootProjectLibDir.resolve("tests.gradle"))
apply(from = rootProjectLibDir.resolve("docker.gradle"))


dependencies {
    implementation(project(":api-elements"))

    // Database
    implementation(libs.flyway.core)
    runtimeOnly(libs.mysql)
    implementation(libs.hikaricp)

    // JPA
    implementation(libs.bundles.hibernate)

    // Thymeleaf
    implementation(libs.bundles.thymeleaf)

    // Jackson
    implementation(libs.bundles.jackson)

    // logging
    implementation(libs.bundles.logging)

    // Others
    runtimeOnly(libs.javax.servlet.api)
    implementation(libs.typesafe.config)
    implementation(libs.javax.inject)
    implementation(libs.passay)
    implementation(libs.bouncycastle)
    implementation(libs.apache.commons.lang)
    implementation(libs.apache.commons.email)
    implementation(libs.apache.commons.csv)
    implementation(libs.commons.codec)
    implementation(libs.commons.validator)
    implementation(libs.commons.io)
    implementation(libs.commons.net)

    // EDI
    implementation(libs.smooks.edi.cartridge)
    implementation(libs.smooks.javabean.cartridge)
    implementation(libs.edi.unedifact.d99b.mapping)
    implementation(libs.edi.unedifact.d99b.binding)

    // Test
    testImplementation(libs.bundles.scalatest)
    testRuntimeOnly(libs.flexmark.all.scalaTest)
}
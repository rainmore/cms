rootProject.name = "cms"

include(
    "api-elements",
    "api",
    "ui"
)


pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

fun scalaArtifact(artifactId: String): String {
    // The version in GWP project may not be the same as the shared version
    // val scalaBranch: String = getProperty("scala.branch").toString()
    val scalaBranch: String = extra["scala.branch"].toString()
    return "%s_%s".format(artifactId, scalaBranch)
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {

            version("plugin-docker", "7.3.0")
            version("plugin-spring-boot", "2.7.2")
            version("plugin-spring-dependency-management", "1.0.11.RELEASE")
            version("plugin-jarTest", "1.0.1")
            version("plugin-git", "4.1.1")
            version("plugin-node-gradle", "3.4.0")

            version("plugin-scalatest", "0.32")
            version("plugin-shadow", "7.1.2")

            // Dependencies: Scala
            version("scala-library", "2.13.8")
            version("scala3-library", "3.1.2")

            // Dependencies: Typesafe
            version("typesafe-config", "1.4.2")
            version("typesafe-logging", "3.9.5")

            // Dependencies: Logging
            version("slf4j", "1.7.36")
            version("logback", "1.2.11")

            // Spring
            version("spring-integration-mail", "5.5.10")

            // Dependencies: Data Storage
            version("flyway", "9.1.2")
            version("hikaricp", "5.0.1")
            version("mysql", "8.0.28")
            version("hibernate-core", "5.6.7.Final")
            version("hibernate-validator", "6.2.3.Final")
            version("querydsl", "5.0.0")

            // Dependencies: Javax APIs
            version("javax-annotation-api", "1.3.2")
            version("javax-validation-api", "2.0.1.Final")
            version("javax-persistence-api", "2.2")

            version("javax-inject", "1")
            version("javax-servlet-api", "4.0.1")

            // Dependencies: Other
            version("bcmail-jdk15on", "1.70")
            version("passay", "1.6.1")

            version("apache-commons-lang", "3.12.0")
            version("apache-commons-email", "1.5")
            version("apache-commons-csv", "1.9.0")
            version("commons-codec", "1.15")
            version("commons-validator", "1.7")
            version("commons-io", "2.11.0")
            version("commons-net", "3.8.0")
            version("jetbrains-annotations", "23.0.0")

            version("webjars-locator", "0.45")

            version("jackson", "2.13.2")
            version("thymeleaf", "3.0.15.RELEASE")
            version("thymeleaf-extra", "3.0.4.RELEASE")
            version("thymeleaf-layout-dialect", "3.0.0")

            // EDI
            version("smooks", "2.0.0-M3")
            version("edi-unedifact", "1.4")

            // Testing
            // Do not upgrade these versions unless ScalaTest is updated
            version("flexmark-scalaTest", "0.62.2")
            version("jfairy", "0.6.5")
            version("mockito", "4.2.0")

            // Plugins
            plugin("git", "org.ajoberstar.grgit").versionRef("plugin-git")
            plugin("scalatest", "com.github.maiflai.scalatest").versionRef("plugin-scalatest")
            plugin("jarTest", "com.github.hauner.jarTest").versionRef("plugin-jarTest")
            plugin("docker", "com.bmuschko.docker-spring-boot-application").versionRef("plugin-docker")
            plugin("shadow", "com.github.johnrengelman.shadow").versionRef("plugin-shadow")
            plugin("spring-boot", "org.springframework.boot").versionRef("plugin-spring-boot")
            plugin("spring-dependency-management", "io.spring.dependency-management").versionRef("plugin-spring-dependency-management")
            plugin("node-gradle", "com.github.node-gradle.node").versionRef("plugin-node-gradle")
            // Scala
            library("scala3-library", "org.scala-lang", scalaArtifact("scala3-library")).versionRef("scala3-library")

            library("typesafe-config", "com.typesafe", "config").versionRef("typesafe-config")
            library("typesafe-logging", "com.typesafe.scala-logging", scalaArtifact("scala-logging")).versionRef("typesafe-logging")

            // Logging
            library("slf4j-api", "org.slf4j", "slf4j-api").versionRef("slf4j")
            library("slf4j-log4j-over-slf4j", "org.slf4j", "log4j-over-slf4j").versionRef("slf4j")
            library("slf4j-nop", "org.slf4j", "slf4j-nop").versionRef("slf4j")
            library("logback-classic", "ch.qos.logback", "logback-classic").versionRef("logback")
            library("logback-core", "ch.qos.logback", "logback-core").versionRef("logback")

            // Spring
            library("spring-integration-mail", "org.springframework.integration", "spring-integration-mail").versionRef("spring-integration-mail")

            // Db
            library("flyway-core", "org.flywaydb", "flyway-core").versionRef("flyway")
            library("mysql", "mysql", "mysql-connector-java").versionRef("mysql")
            library("hikaricp", "com.zaxxer", "HikariCP").versionRef("hikaricp")
            library("hibernate-core", "org.hibernate", "hibernate-core").versionRef("hibernate-core")
            library("hibernate-entitymanager", "org.hibernate", "hibernate-entitymanager").versionRef("hibernate-core")
            library("hibernate-java8", "org.hibernate", "hibernate-java8").versionRef("hibernate-core")
            library("hibernate-validator", "org.hibernate.validator", "hibernate-validator").versionRef("hibernate-validator")

            library("querdsl-apt", "com.querydsl", "querydsl-apt").versionRef("querydsl")
            library("querdsl-sql", "com.querydsl", "querydsl-sql").versionRef("querydsl")
            library("querdsl-jpa", "com.querydsl", "querydsl-jpa").versionRef("querydsl")
            library("querdsl-scala", "com.querydsl", "querydsl-scala").versionRef("querydsl")

            // JAVAX
            library("javax-validation-api", "javax.validation", "validation-api").versionRef("javax-validation-api")
            library("javax-persistence-api", "javax.persistence", "javax.persistence-api").versionRef("javax-persistence-api")
            library("javax-annotation-api", "javax.annotation", "javax.annotation-api").versionRef("javax-annotation-api")
            library("javax-inject", "javax.inject", "javax.inject").versionRef("javax-inject")
            library("javax-servlet-api", "javax.servlet", "javax.servlet-api").versionRef("javax-servlet-api")

            library("webjars-locator", "org.webjars", "webjars-locator").versionRef("webjars-locator")

            library("passay", "org.passay", "passay").versionRef("passay")
            library("bouncycastle", "org.bouncycastle", "bcmail-jdk15on").versionRef("bcmail-jdk15on")

            library("apache-commons-lang", "org.apache.commons", "commons-lang3").versionRef("apache-commons-lang")
            library("apache-commons-email", "org.apache.commons", "commons-email").versionRef("apache-commons-email")
            library("apache-commons-csv", "org.apache.commons", "commons-csv").versionRef("apache-commons-csv")
            library("commons-codec", "commons-codec", "commons-codec").versionRef("commons-codec")
            library("commons-validator", "commons-validator", "commons-validator").versionRef("commons-validator")
            library("commons-io", "commons-io", "commons-io").versionRef("commons-io")
            library("commons-net", "commons-net", "commons-net").versionRef("commons-net")
            library("jetbrains-annotations", "org.jetbrains", "annotations").versionRef("jetbrains-annotations")

            // Thymeleaf
            library("thymeleaf", "org.thymeleaf", "thymeleaf").versionRef("thymeleaf")
            library("thymeleaf-spring5", "org.thymeleaf", "thymeleaf-spring5").versionRef("thymeleaf")
            library("thymeleaf-extras-springsecurity5", "org.thymeleaf.extras", "thymeleaf-extras-springsecurity5").versionRef("thymeleaf-extra")
            library("thymeleaf-extras-java8time", "org.thymeleaf.extras", "thymeleaf-extras-java8time").versionRef("thymeleaf-extra")
            library("thymeleaf-layout-dialect", "nz.net.ultraq.thymeleaf", "thymeleaf-layout-dialect").versionRef("thymeleaf-layout-dialect")

            // Jackson
            library("jackson-core", "com.fasterxml.jackson.core", "jackson-core").versionRef("jackson")
            library("jackson-datatype-jdk8", "com.fasterxml.jackson.datatype", "jackson-datatype-jdk8").versionRef("jackson")
            library("jackson-datatype-jsr310", "com.fasterxml.jackson.datatype", "jackson-datatype-jsr310").versionRef("jackson")
            library("jackson-datatype-hibernate5", "com.fasterxml.jackson.datatype", "jackson-datatype-hibernate5").versionRef("jackson")
            library("jackson-module-scala", "com.fasterxml.jackson.module", scalaArtifact("jackson-module-scala")).versionRef("jackson")

            // EDI
            library("smooks-edi-cartridge", "org.smooks.cartridges.edi", "smooks-edi-cartridge").versionRef("smooks")
            library("smooks-javabean-cartridge", "org.smooks.cartridges", "smooks-javabean-cartridge").versionRef("smooks")
            library("edi-unedifact-d99b-mapping", "org.milyn.edi.unedifact", "d99b-mapping").versionRef("edi-unedifact")
            library("edi-unedifact-d99b-binding", "org.milyn.edi.unedifact", "d99b-binding").versionRef("edi-unedifact")

            // Test
            library("scalactic3", "org.scalactic", scalaArtifact("scalactic_sjs1")).version("3.2.12")
            library("scalatest3", "org.scalatest", scalaArtifact("scalatest_sjs1")).version("3.2.12")
            library("scalatest3-plus-mockito", "org.scalatestplus", scalaArtifact("mockito-4-2")).version("3.2.11.0")
            library("scalatest3-plus-junit", "org.scalatestplus", scalaArtifact("junit-4-13")).version("3.2.12.0")

            library("flexmark-all-scalaTest", "com.vladsch.flexmark", "flexmark-all").versionRef("flexmark-scalaTest")
            library("jfairy", "com.devskiller", "jfairy").versionRef("jfairy")

            // Bundles
            bundle("scala3", listOf("scala3-library"))
            bundle("logging", listOf("slf4j-api", "slf4j-log4j-over-slf4j", "logback-classic", "logback-core", "typesafe-logging"))
            bundle("hibernate", listOf("hibernate-core", "hibernate-entitymanager", "hibernate-java8", "hibernate-validator", "javax-validation-api"))
            bundle("thymeleaf", listOf("thymeleaf", "thymeleaf-spring5", "thymeleaf-extras-springsecurity5", "thymeleaf-extras-java8time", "thymeleaf-layout-dialect"))
            bundle("jackson", listOf("jackson-core", "jackson-datatype-jdk8", "jackson-datatype-jsr310", "jackson-datatype-hibernate5", "jackson-module-scala"))
            bundle("scalatest", listOf("scalactic3", "scalatest3", "scalatest3-plus-mockito", "scalatest3-plus-junit", "jfairy"))
        }
    }
}



plugins {
    id 'org.springframework.boot'                    apply false
    id 'com.github.hauner.jarTest'                   apply false
    id 'com.bmuschko.docker-spring-boot-application' apply false
    id 'com.github.node-gradle.node'                 apply false
    id 'org.ajoberstar.grgit'

    id 'idea'
}

description = 'Content Manage System'
group = 'com.rainmore.cms'
version '2.0.0'

defaultTasks 'clean'

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

tasks.named('wrapper') {
    gradleVersion = '7.5'
    distributionType = Wrapper.DistributionType.ALL
}

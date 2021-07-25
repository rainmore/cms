apply plugin: 'application'

defaultTasks 'clean'

application {
    mainClass.set([rootProject.group, 'Application'].join('.'))
}

apply from: '../project/assemble.gradle'
apply from: '../project/utils.gradle'
apply from: '../project/scala.gradle'
apply from: '../project/logging.gradle'
apply from: '../project/misc.gradle'
apply from: '../project/jackson.gradle'
apply from: '../project/db.gradle'
apply from: '../project/spring.gradle'
apply from: '../project/thymeleaf.gradle'
apply from: '../project/tests.gradle'
apply from: '../project/docker.gradle'
apply from: '../project/flyway.gradle'
apply from: '../project/swagger2.gradle'


dependencies {
    implementation project(':api-elements')
}

idea {
    module {
        // fix IDEA out folder
        outputDir file('build/classes/scala/main')
        testOutputDir file('build/classes/scala/test')
        resourceDirs += file('build/resources/main')
        testResourceDirs += file('build/resources/test')
    }
}
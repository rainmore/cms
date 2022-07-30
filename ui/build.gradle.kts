apply plugin: 'com.github.node-gradle.node'

node {
    // Version of node to use.
    version = '16.6.0'

    // Version of npm to use.
    npmVersion = '7.19.1'

    download = true
}

description = 'UI webapp'
group = 'com.rainmore.webapp'

tasks.register('clean', Delete) {
    group 'build'
    delete 'dist'
    followSymlinks = true
}

tasks.register('runBuild', NpmTask) {
    group 'build'
    dependsOn npmInstall
    args = ['run', 'build']
}

tasks.register('watch', NpmTask) {
    group 'build'
    dependsOn npmInstall
    args = ['run', 'watch']
}

tasks.register('build') {
    dependsOn 'clean'
    dependsOn 'runBuild'
}

tasks.register('bootRun') {
    group 'application'
    dependsOn 'clean'
    dependsOn 'watch'
}

tasks.register('ngUpdate', NpxTask) {
    group 'build'
    dependsOn npmInstall
    command = 'ng'
    args = ['update']
}

tasks.register('ngHelp', NpxTask) {
    group 'build'
    dependsOn npmInstall
    command = 'ng'
    args = ['help']
}

tasks.register('ngRun', NpxTask) {
    group 'application'
    dependsOn npmInstall
    command = 'ng'
    args = ['serve', '--open']
}

tasks.register('ngBuild', NpxTask) {
    dependsOn npmInstall
    command = 'ng'
    args = ['build', '--configuration', 'production']
}

/**
 * ./gradlew ui:gc -Pn=lalal
 */
tasks.register('gc', NpxTask) {
    group 'build'
    command = 'ng'
    args = ['generate', 'component', n, '--flat']
}

/**
 * ./gradlew ui:gs -Pn=lalal
 */
tasks.register('gs', NpxTask) {
    group 'build'
    command = 'ng'
    args = ['generate', 'service', n]
}

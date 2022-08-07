import com.github.gradle.node.npm.task.NpxTask
import com.github.gradle.node.task.NodeTask
import com.github.gradle.node.yarn.task.YarnTask
import org.gradle.api.tasks.Delete

plugins {
  alias(libs.plugins.node.gradle)

  id("idea")
}

// See node example in https://github.com/node-gradle/gradle-node-plugin/blob/master/src/test/resources/fixtures/kotlin/build.gradle.kts
node {
  // Version of node to use.
  version.set("16.16.0")

  // Version of npm to use.
  npmVersion.set("")
  yarnVersion.set("")

  download.set(true)
}

tasks.register<Delete>("reset") {
  group = "build"
  dependsOn("clean")
  setFollowSymlinks(true)
  delete(".angular", "node_modules", ".gradle")
}

tasks.register<Delete>("clean") {
  group = "build"
  setFollowSymlinks(true)
  dependsOn("yarn_cache_clean")
  delete("dist")
}

tasks.register<YarnTask>("watch") {
  group = "build"
  dependsOn("yarn_install")
  yarnCommand.set(listOf("run", "watch"))
}

tasks.register<YarnTask>("build") {
  group = "build"
  dependsOn( "yarn_install")
  yarnCommand.set(listOf("run", "build"))
}

tasks.register<YarnTask>("update") {
  group = "build"
  yarnCommand.set(listOf("ng", "update", "@angular/cli", "@angular/core"))
}

tasks.register<YarnTask>("run") {
  group = "application"
  dependsOn("yarn_install")
  yarnCommand.set(listOf("ng", "serve"))
}

// task to switching yarn to Plug'n'Play, see https://yarnpkg.com/getting-started/migration#switching-to-plugnplay
// TODO to complete it once angular officially support it and dropped node_modules folder
tasks.register<YarnTask>("yarn-doctor") {
  group = "build"
  yarnCommand.set(listOf("dlx", "@yarnpkg/doctor"))
}

tasks.register<YarnTask>("test") {
  group = "verification"
  dependsOn("yarn_install")
  yarnCommand.set(listOf("ng", "test"))
}

// TODO to rework following task in the future
//tasks.register("bootRun") {
//  group = "application"
//  dependsOn("clean", "watch")
//  dependsOn "clean"
//}
//
//tasks.register<NpxTask>("ngUpdate") {
//  group = "build"
//  dependsOn npmInstall
//    command = "ng"
//  args.set(listOf("update"))
//}
//
//tasks.register<NpxTask>("ngHelp") {
//  group = "build"
//  dependsOn npmInstall
//    command = "ng"
//  args = ["help"]
//}
//
//tasks.register<NpxTask>("ngRun") {
//  group = "application"
//  dependsOn npmInstall
//    command.set("ng")
//  args.set(listOf("serve", "--open"))
//}
//
//tasks.register<NpxTask>("ngBuild") {
//  group = "build"
//  command.set("ng")
//  args.set(listOf("build", "--configuration", "production"))
//}
//
///**
// * ./gradlew ui:gc -Pn=lalal
// */
//tasks.register<NpxTask>("gc") {
//  group = "build"
//  command.set("ng")
//  args.set(listOf("generate", "component", n, "--flat"))
//}
//
///**
// * ./gradlew ui:gs -Pn=lalal
// */
//tasks.register<NpxTask>("gs", NpxTask) {
//  group = "build"
//  command.set("ng")
//  args.set(listOf("generate", "service", n))
//}

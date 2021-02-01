import sbt.Keys._
import sbt._
import uk.gov.hmrc.versioning.SbtGitVersioning

val appName = "play-allowlist-filter"

lazy val playAllowlistFilter = (project in file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(
    name := appName,
    majorVersion := 1,
    makePublicallyAvailableOnBintray := true,
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test,
    scalaVersion := "2.12.12",
    /*
     * set fork in Test true to avoid error when PLAY_VERSION=2.7:
     * - java.lang.AbstractMethodError: play.api.i18n.Messages$MessagesParser.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar()Lscala/util/DynamicVariable;
     * see thread at: https://github.com/scala/scala-parser-combinators/issues/197
     */
    fork in Test := true
  )
  .settings(PlayCrossCompilation.playCrossCompilationSettings)

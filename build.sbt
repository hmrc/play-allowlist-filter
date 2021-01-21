import sbt.Keys._
import sbt._
import uk.gov.hmrc.versioning.SbtGitVersioning

val appName = "play-allowlist-filter"

val compileDependencies = PlayCrossCompilation.dependencies(
  play26 = Seq(
    "com.typesafe.play" %% "play" % "2.6.20"
  ),
  play27 = Seq(
    "com.typesafe.play" %% "play" % "2.7.4"
  ),
  play28 = Seq(
    "com.typesafe.play" %% "play" % "2.8.7"
  )
)

val testDependencies = PlayCrossCompilation.dependencies(
    shared = Seq(
      "org.scalatest"          %% "scalatest"          % "3.0.9"      % Test
    ),
    play26 = Seq(
      "org.pegdown"            %  "pegdown"            % "1.6.0"      % Test,
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2"      % Test
    ),
    play27 = Seq(
      "org.pegdown"            %  "pegdown"            % "1.6.0"      % Test,
      "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3"      % Test
    ),
    play28 = Seq(
      "com.vladsch.flexmark"   %  "flexmark-all"       % "0.35.10"    % Test,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0"      % Test
    )
)

lazy val playAllowlistFilter = (project in file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(
    name := appName,
    majorVersion := 1,
    makePublicallyAvailableOnBintray := true,
    libraryDependencies ++= compileDependencies ++ testDependencies,
    scalaVersion := "2.12.12",
    /*
     * set fork in Test true to avoid error when PLAY_VERSION=2.7:
     * - java.lang.AbstractMethodError: play.api.i18n.Messages$MessagesParser.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar()Lscala/util/DynamicVariable;
     * see thread at: https://github.com/scala/scala-parser-combinators/issues/197
     */
    fork in Test := true
  )
  .settings(PlayCrossCompilation.playCrossCompilationSettings)

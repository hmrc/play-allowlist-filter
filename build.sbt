import sbt.Keys._
import sbt._
import uk.gov.hmrc.versioning.SbtGitVersioning

val scala2_12 = "2.12.15"
val scala2_13 = "2.13.7"

lazy val playAllowlistFilter = Project("play-allowlist-filter", file("."))
  .settings(
    majorVersion := 1,
    isPublicArtefact := true,
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test,
    scalaVersion := scala2_12,
    crossScalaVersions := Seq(scala2_12, scala2_13)
  )
  .settings(PlayCrossCompilation.playCrossCompilationSettings)

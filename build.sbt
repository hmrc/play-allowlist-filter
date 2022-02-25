import sbt.Keys._
import sbt._
import uk.gov.hmrc.versioning.SbtGitVersioning

val scala2_12 = "2.12.15"
val scala2_13 = "2.13.7"

lazy val playAllowlistFilter = Project("play-allowlist-filter-play-28", file("."))
  .settings(
    majorVersion := 1,
    isPublicArtefact := true,
    libraryDependencies ++= AppDependencies.compilePlay28 ++ AppDependencies.testPlay28,
    scalaVersion := scala2_12,
    crossScalaVersions := Seq(scala2_12, scala2_13)
  )

import sbt.Keys._
import sbt._

val scala2_13 = "2.13.16"
val scala3    = "3.3.5"

lazy val commonSettings = Seq(
  organization       := "uk.gov.hmrc",
  majorVersion       := 1,
  isPublicArtefact   := true,
  scalaVersion       := scala2_13,
  crossScalaVersions := Seq(scala2_13, scala3)
)

lazy val library = (project in file("."))
  .settings(
    publish / skip := true,
    commonSettings
  )
  .aggregate(
    playAllowlistFilter,
    playAllowlistFilterPlay28
  )

lazy val playAllowlistFilterPlay28 = Project("play-allowlist-filter-play-30", file("play-allowlist-filter-play-30"))
  .settings(
    commonSettings,
    libraryDependencies ++= AppDependencies.compilePlay30 ++ AppDependencies.testPlay30
  )

// empty artefact, exists to ensure eviction of previous play-allowlist-filter jar which has now moved into play-allowlist-filter-play-xx
lazy val playAllowlistFilter = Project("play-allowlist-filter", file("play-allowlist-filter"))
  .settings(
    commonSettings
  )

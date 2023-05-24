import sbt.Keys._
import sbt._

val scala2_12 = "2.12.17"
val scala2_13 = "2.13.8"

lazy val commonSettings = Seq(
  organization       := "uk.gov.hmrc",
  majorVersion       := 1,
  isPublicArtefact   := true,
  scalaVersion       := scala2_12,
  crossScalaVersions := Seq(scala2_12, scala2_13)
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

lazy val playAllowlistFilterPlay28 = Project("play-allowlist-filter-play-28", file("play-allowlist-filter-play-28"))
  .settings(
    commonSettings,
    libraryDependencies ++= AppDependencies.compilePlay28 ++ AppDependencies.testPlay28
  )

// empty artefact, exists to ensure eviction of previous play-allowlist-filter jar which has now moved into play-allowlist-filter-play-xx
lazy val playAllowlistFilter = Project("play-allowlist-filter", file("play-allowlist-filter"))
  .settings(
    commonSettings
  )

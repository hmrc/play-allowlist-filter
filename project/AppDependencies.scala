import sbt._

object AppDependencies {

  val compilePlay28 = Seq(
    "com.typesafe.play" %% "play" % "2.8.8"
  )

  lazy val testPlay28: Seq[ModuleID] = Seq(
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0"      % Test,
    "com.vladsch.flexmark"   %  "flexmark-all"       % "0.35.10"    % Test
  )
}

import sbt._

object AppDependencies {

  val compilePlay30 = Seq(
    "org.playframework" %% "play" % "3.0.9"
  )

  lazy val testPlay30: Seq[ModuleID] = Seq(
    "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.2"     % Test,
    "com.vladsch.flexmark"   %  "flexmark-all"       % "0.64.8"    % Test
  )
}

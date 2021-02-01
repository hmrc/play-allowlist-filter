import sbt._

object AppDependencies {

  val compile = PlayCrossCompilation.dependencies(
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

  lazy val test: Seq[ModuleID] = PlayCrossCompilation.dependencies(
    play26 = Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.3"      % Test,
      "org.pegdown"            %  "pegdown"            % "1.6.0"      % Test
    ),
    play27 = Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3"      % Test,
      "org.pegdown"            %  "pegdown"            % "1.6.0"      % Test
    ),
    play28 = Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0"      % Test,
      "com.vladsch.flexmark"   %  "flexmark-all"       % "0.35.10"    % Test
    )
  )

}

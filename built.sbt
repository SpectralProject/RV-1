ThisBuild / scalaVersion := "2.13.7"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "Spectro"

val chiselVersion = "3.5.1"

lazy val rocketChip = RootProject(file("./rocket-chip"))

lazy val root = (project in file("."))
  .settings(
    name := "ChCPU",
    addCompilerPlugin(
      "edu.berkeley.cs" % "chisel3-plugin" % "3.5.0" cross CrossVersion.full
    ),
    libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.5.0",
    libraryDependencies += "edu.berkeley.cs" %% "chiseltest" % "0.5.0" % "test",
    // rocket chip generator! Doesnt work...
    // libraryDependencies += "edu.berkeley.cs" %% "rocketchip" % "1.2.6",
    scalacOptions ++= Seq(
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-Xcheckinit"
    )
  )

name := "BulkySourcesPlugin"

ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.dm4x.sbtplugins"

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .enablePlugins(BulkySourcesPlugin)
  .settings(
    name := "BulkySourcesPlugin",
  )
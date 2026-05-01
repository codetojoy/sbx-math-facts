name := """sbx-math-facts"""
organization := "net.codetojoy"

version := "0.1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.14"

javacOptions ++= Seq("--release", "21")

libraryDependencies += guice
libraryDependencies += "org.junit.jupiter" % "junit-jupiter" % "5.10.2" % Test
libraryDependencies += "net.aichler" % "jupiter-interface" % "0.11.1" % Test

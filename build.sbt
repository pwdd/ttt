name := "tttWebApp"

version := "1.0"

scalaVersion := "2.12.1"

resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "com.github.pwdd" %% "tttcore" % "0.0.1.1"
libraryDependencies += "com.github.pwdd.HTTPServer" % "HTTPServer" % "0.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.0-M1"

enablePlugins(JavaServerAppPackaging)
name := "tttWebApp"

version := "1.0"

scalaVersion := "2.12.1"

resolvers += Resolver.mavenLocal

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "default" %% "tttcore" % "0.0.1"
libraryDependencies += "com.github.pwdd.HTTPServer" % "HTTPServer" % "0.1"
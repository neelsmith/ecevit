lazy val root = (project in file(".")).
  settings(
    name := "ecevit",
    version := "1.0",
    scalaVersion := "2.11.7"
  )

// libraryDependencies +=  "org.json4s" %% "json4s-native" % "3.3.0.RC2"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.0" % "test"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in Test ++= Seq("-Yrangepos")



//mainClass in assembly := Some("PandocJsonFilter")

name := "Example"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
    exclude("org.scala-lang", "scala-reflect")
    exclude("org.scala-lang.modules", "scala-xml_2.11")
)
name := "play-java"

version := "1.0-SNAPSHOT"


// Some dependencies like `javacpp` are packaged with maven-plugin packaging
classpathTypes += "maven-plugin"


lazy val root = (project in file("."))
  .enablePlugins(
    PlayJava,
    PlayEnhancer,
    PlayAkkaHttpServer,
    PlayAkkaHttp2Support,
    PlayScala,
    JavaAppPackaging
  )

scalaVersion := "2.12.12"


resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  "ImageJ Releases" at "http://maven.imagej.net/content/repositories/releases/",
  // Use local maven repo for local javacv builds
  "Local Maven Repository" at "file:///" + Path.userHome.absolutePath + "/.m2/repository"
)

val platform = org.bytedeco.javacpp.Loader.getPlatform
libraryDependencies ++= Seq(
  ehcache,
  guice,

  "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.1.14",
  "com.typesafe.play" %% "play-ws-standalone-json" % "1.0.1",
  "com.typesafe.play" %% "play-ws-standalone-xml" % "1.0.1",

  "com.typesafe.play" %% "play-json" % "2.9.1",
  "org.joda" % "joda-convert" % "1.8.1",

  "com.typesafe.play" %% "play-iteratees" % "2.6.1",
  "com.typesafe.play" %% "play-iteratees-reactive-streams" % "2.6.1",

  "org.bytedeco" % "javacv" % "1.3.2",
  "org.bytedeco" % "javacpp" % "1.3.2",
  "org.bytedeco" % "javacv-platform" % "1.3.2",

  "org.bytedeco.javacpp-presets" % "flandmark" % "1.07-0.11" classifier "",
  "org.bytedeco.javacpp-presets" % "flandmark" % "1.07-0.11" classifier platform,
  "org.bytedeco.javacpp-presets" % "opencv" % "3.2.0-1.3" classifier "",
  "org.bytedeco.javacpp-presets" % "opencv" % "3.2.0-1.3"  classifier platform,

  "org.scalatest" %% "scalatest" % "3.0.3" % Test,
  "net.imagej"                   % "ij"              % "1.49v",
  "junit"                        % "junit"           % "4.12" % "test",
  "com.novocode"                 % "junit-interface" % "0.11" % "test",
  "com.typesafe.akka" %% "akka-actor" % "2.6.10",
  "com.typesafe.akka" %% "akka-agent" % "2.5.31",
  "com.typesafe.akka" %% "akka-camel" % "2.5.31",
  "com.typesafe.akka" %% "akka-cluster" % "2.6.10",
  "com.typesafe.akka" %% "akka-cluster-metrics" % "2.6.10",
  "com.typesafe.akka" %% "akka-cluster-sharding" % "2.6.10",
  "com.typesafe.akka" %% "akka-cluster-tools" % "2.6.10",
  "com.typesafe.akka" %% "akka-distributed-data" % "2.6.10",
  "com.typesafe.akka" %% "akka-multi-node-testkit" % "2.6.10",
  "com.typesafe.akka" %% "akka-osgi" % "2.5.3",
  "com.typesafe.akka" %% "akka-persistence" % "2.6.10",
  "com.typesafe.akka" %% "akka-persistence-query" % "2.6.10",
  "com.typesafe.akka" %% "akka-persistence-tck" % "2.5.3",
  "com.typesafe.akka" %% "akka-remote" % "2.6.10",
  "com.typesafe.akka" %% "akka-slf4j" % "2.6.10",
  "com.typesafe.akka" %% "akka-stream" % "2.6.10",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.6.10",
  "com.typesafe.akka" %% "akka-testkit" % "2.6.10",
  "com.typesafe.akka" %% "akka-typed" % "2.5.8",
  "com.typesafe.akka" %% "akka-contrib" % "2.5.31"
)


shellPrompt in ThisBuild := { state => "sbt:" + Project.extract(state).currentRef.project + "> "}
autoCompilerPlugins := true
javaOptions += "-Xmx2G"

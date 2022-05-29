import sbt.Keys.scalacOptions

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.2"

val http4sVersion = "0.23.11"

val scalacOpt = Seq(
  "-deprecation",
  "UTF-8",
  "future",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings",
  "-Ykind-projector"
)

val packagePrefix = Some("me.zahara.moddedminecraft")

val commonLibraryDependencies = Seq(
  "org.typelevel" %% "cats-core" % "2.7.0",
  "org.typelevel" %% "cats-effect" % "3.3.11",
  "org.typelevel" %% "cats-collections-core" % "0.9.3"
)

lazy val common = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("common"))
  .settings(
    name := "common",
    idePackagePrefix := packagePrefix,
    scalacOptions ++= scalacOpt,
    libraryDependencies ++= Seq(

    ) ++ commonLibraryDependencies
  )

lazy val server = project
  .enablePlugins(JavaAppPackaging)
  .in(file("server"))
  .settings(
    name := "server",
    idePackagePrefix := packagePrefix,
    scalacOptions ++= scalacOpt,
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-blaze-server" % http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % http4sVersion,
      "org.tpolecat" %% "doobie-core"      % "1.0.0-RC1",
      "org.tpolecat" %% "doobie-postgres"  % "1.0.0-RC1",          // Postgres driver 42.3.1 + type mappings.
    ) ++ commonLibraryDependencies
  )
  .dependsOn(common.jvm)


val ScalaJsReactVer = "2.1.1"

lazy val browser = project
  .enablePlugins(ScalaJSPlugin)
  .in(file("browser"))
  .settings(
    name := "browser",
    idePackagePrefix := packagePrefix,
    scalacOptions ++= scalacOpt,
    libraryDependencies ++= Seq(
      "com.github.japgolly.scalajs-react" %%% "core-ext-cats"        % ScalaJsReactVer,
      "com.github.japgolly.scalajs-react" %%% "core-ext-cats_effect" % ScalaJsReactVer,
      "com.github.japgolly.scalajs-react" %%% "core"                 % ScalaJsReactVer
    ) ++ commonLibraryDependencies,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    scalaJSUseMainModuleInitializer := true,
  )
  .dependsOn(common.js)

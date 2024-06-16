# Examen DO TP

10 steps.

## Init sbt project

Say hello, build a simple hello world application.

```bash
sbt runMain do.HelloWorld

```

Should output: "Hello World"


## Hello ZIO World

* ZIO dependencies
  * "dev.zio" %% "zio" % "2.1.3"
  * "dev.zio" %% "zio-streams" % "2.1.3"

Build a simple hello world application using ZIO.

```bash
sbt runMain do.ZIOHelloWorld

```

Should output: "Hello World"

## Http 

### Http Server

Build a simple http server using ZIO.

```bash
sbt runMain zio.HttpServer

```

Should start a server on port 8080 that returns "Hello World" on every request.

```bash
curl http://localhost:8080/wtf
```


### Http Client

Build a simple http client using ZIO.

```bash
sbt runMain zio.HttpClient

```

Should output: "Hello World"


### Tapir 

* Tapir dependencies
  * "com.softwaremill.sttp.tapir" %% "tapir-zio" % "0.17.0"
  * "com.softwaremill.sttp.tapir" %% "tapir-core" % "0.17.0"
  * "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % "0.17.0"


Build a simple http server using Tapir.

## ScalaJS (Optional)

* ScalaJS dependencies
  * "org.scala-js" %% "scalajs-dom" % "1.1.0"

## Tapir Http Client

* Tapir dependencies
  * "com.softwaremill.sttp.tapir" %% "tapir-zio" % "0.17.0"
  * "com.softwaremill.sttp.tapir" %% "tapir-core" % "0.17.0"

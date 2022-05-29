package me.zahara.moddedminecraft

import cats.*
import cats.data.*
import cats.effect.*
import cats.implicits.*
import doobie.*
import doobie.free.ConnectionIO
import doobie.implicits.*
import doobie.util.transactor.Transactor
import org.http4s.*
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.dsl.io.*
import org.http4s.implicits.*
import org.http4s.server.*
import org.http4s.server.blaze.*

import scala.concurrent.ExecutionContext

object Main extends IOApp:
  val app = HttpRoutes.of[IO] {
    case GET -> Root / "hello" / name => Ok(s"Hello, $name.")
    case _ => BadRequest()
  }.orNotFound

  val xa: Transactor.Aux[IO, Unit] = Transactor.fromDriverManager[IO]("org.postgresql.Driver", "jdbc:postgresql:world", "postgres", "")

  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(app)
      .resource
      .use(_ => IO.never)
      .as(ExitCode.Success)
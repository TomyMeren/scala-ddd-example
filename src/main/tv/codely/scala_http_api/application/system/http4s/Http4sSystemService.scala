package tv.codely.scala_http_api.application.system.http4s

import cats.effect.Effect
import cats.implicits._
import org.http4s._
import org.http4s.dsl.Http4sDsl
import tv.codely.scala_http_api.application.system.api.System
import tv.codely.scala_http_api.application.user.http4s.Http4sUserController

class Http4sSystemService[P[_]: Effect](system: System[P]) extends Http4sDsl[P] {
  //HttpRoutes[P]
  val service: HttpService[P] = Http4sStatusService().service <+>
    Http4sUserController(system.usersSearcher, system.userRegistrar).service
}

object Http4sSystemService {

  import scala.concurrent.ExecutionContext
  import fs2.Stream
  import org.http4s.server.blaze._
  import org.http4s.server.blaze.BlazeBuilder
  import fs2.StreamApp, StreamApp.ExitCode
  import com.typesafe.config.{Config, ConfigFactory}
  import tv.codely.scala_http_api.application.system.akkaHttp.HttpServerConfig

  class App[F[_]](system: Config => System[F])(implicit
                                               E: Effect[F],
                                               ec: ExecutionContext)
      extends StreamApp[F] {
    lazy val appConfig                    = ConfigFactory.load("application")
    lazy val HttpServerConfig(host, port) = HttpServerConfig(ConfigFactory.load("http-server"))

    val httpService = new Http4sSystemService(system(appConfig))(E).service

    def stream(args: List[String], requestShutdown: F[Unit]): Stream[F, ExitCode] =
      BlazeBuilder[F]
        .bindHttp(port, host)
        .mountService(httpService, "/")
        .serve

    /*val httpApp = Router("/" -> httpService.orNotFound)

        def run(args: List[String]): IO[ExitCode] =
          BlazeServerBuilder[IO](global)
            .bindHttp(port, host)
            .withHttpApp(httpApp)
            .resource
            .use(_ => IO.never)
            .as(ExitCode.Success)*/
  }
}

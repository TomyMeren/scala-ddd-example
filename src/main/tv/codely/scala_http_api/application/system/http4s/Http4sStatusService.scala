package tv.codely.scala_http_api.application.system.http4s

import cats.effect._
import org.http4s._
import org.http4s.dsl.Http4sDsl
import io.circe.literal._
import org.http4s.circe._

case class Http4sStatusService[P[_]: Effect]() extends Http4sDsl[P] {
  val service = HttpService[P] { //HttpRoutes.of
    case GET -> Root / "status" =>
      Ok(json"""{"status":"ok"}""")
  }
}

package tv.codely.scala_http_api.application.user.http4s

import cats.effect._
import io.circe.literal._

import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

case class Http4sUserController[F[_]: Effect](system: System[F]) extends Http4sDsl[F] {
  val service = HttpRoutes.of[F] {
    case GET -> Root / "user" =>
      Ok()
  }
}

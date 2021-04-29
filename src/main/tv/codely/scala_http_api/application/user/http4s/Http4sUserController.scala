package tv.codely.scala_http_api.application.user.http4s

import cats.effect._
import cats.syntax.apply._
import cats.syntax.flatMap._
import org.http4s._
import org.http4s.dsl.Http4sDsl
import org.http4s.circe._
import io.circe.generic.auto._
import io.circe.syntax._
import tv.codely.scala_http_api.application.system.http4s.Decoders._
import tv.codely.scala_http_api.application.user.api.{User, UserId, UserName, UserRegister, UsersSearcher}

case class Http4sUserController[P[_]: Effect](userSearcher: UsersSearcher[P], userRegister: UserRegister[P])
    extends Http4sDsl[P] {

  val service = HttpService[P] { //HttpRoutes.of
    case GET -> Root / "users" =>
      userSearcher.all().flatMap { users =>
        Ok(users.asJson)
      }

      //La original
    case req @ POST -> Root / "users2" =>
      req.as[(String, String)] >>= {
        case (id, name) =>
          userRegister.register(UserId(id), UserName(name)) *>
            NoContent()
      }

      //Con user
    case req @ POST -> Root / "users" =>
      req.as[User] >>= {
        case user =>
          userRegister.register(user.id, user.name) *>
            NoContent()
      }
  }
}

package tv.codely.scala_http_api.application.user.akkaHttp

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import tv.codely.scala_http_api.application.user.api.UserRegister
import tv.codely.scala_http_api.module.user.domain.{UserId, UserName}

import scala.concurrent.Future

final class UserPostController(registrar: UserRegister[Future]) {
  def post(id: String, name: String): StandardRoute = {
    registrar.register(UserId(id), UserName(name))

    complete(HttpResponse(NoContent))
  }
}

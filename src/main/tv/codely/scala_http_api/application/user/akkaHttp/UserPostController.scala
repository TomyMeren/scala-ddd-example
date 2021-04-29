package tv.codely.scala_http_api.application.user.akkaHttp

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import tv.codely.scala_http_api.application.user.api.{UserId, UserName, UserRegister}

import scala.concurrent.{ExecutionContext, Future}

final class UserPostController(registrar: UserRegister[Future])(implicit executionContext: ExecutionContext) {
  def post(id: String, name: String): StandardRoute =
    complete(registrar.register(UserId(id), UserName(name)).map { _ =>
      HttpResponse(NoContent)
    })

}

package tv.codely.scala_http_api.entry_point.controller.user

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.module.user.application.create.UserCreator
import tv.codely.scala_http_api.module.user.domain.{UserId, UserName}

final class UserPostController (user: UserCreator) extends SprayJsonSupport with DefaultJsonProtocol {
  def post(userId:String, userName:String) = {
    user.create(UserId(userId), UserName(userName))
    complete(HttpResponse(NoContent))
  }

}

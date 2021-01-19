package tv.codely.scala_http_api.entry_point.controller.user

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.module.user.application.UserSearcher
import tv.codely.scala_http_api.module.user.infrastructure.UserMarshaller._

final class UserGetController(user: UserSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(user.all())
}

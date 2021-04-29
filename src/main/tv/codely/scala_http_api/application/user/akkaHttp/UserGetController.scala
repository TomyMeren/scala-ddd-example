package tv.codely.scala_http_api.application.user.akkaHttp

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.application.user.repo_publisher.UsersSearcherRepo
import tv.codely.scala_http_api.module.user.infrastructure.marshaller.UserJsonFormatMarshaller._

import scala.concurrent.Future

final class UserGetController(searcher: UsersSearcherRepo[Future]) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}

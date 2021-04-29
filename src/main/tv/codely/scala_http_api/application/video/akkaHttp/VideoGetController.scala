package tv.codely.scala_http_api.application.video.akkaHttp

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.application.video.akkaHttp.marshaller.VideoJsonFormatMarshaller._
import tv.codely.scala_http_api.application.video.api.VideoSearcher

import scala.concurrent.Future

final class VideoGetController(searcher: VideoSearcher[Future]) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}

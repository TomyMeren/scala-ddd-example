package tv.codely.scala_http_api.entry_point.controller.video

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.module.video.application.search.VideoSearcher
import tv.codely.scala_http_api.module.video.infrastructure.marshaller.VideoJsonFormatMarshaller._

final class VideoGetController(video : VideoSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute =  complete(video.all())
}

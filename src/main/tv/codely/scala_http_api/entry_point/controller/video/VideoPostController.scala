package tv.codely.scala_http_api.entry_point.controller.video

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import spray.json.DefaultJsonProtocol
import akka.http.scaladsl.server.Directives.complete
import tv.codely.scala_http_api.module.video.application.create.VideoCreator
import tv.codely.scala_http_api.module.video.domain.{VideoCategory, VideoDuration, VideoId, VideoTitle}

import scala.concurrent.duration.Duration

final class VideoPostController(video : VideoCreator) extends SprayJsonSupport with DefaultJsonProtocol{

  def post(id:String,title:String,duration:Duration, category:String) = {
    video.create(VideoId(id), VideoTitle(title), VideoDuration(duration), VideoCategory(category))
    complete(HttpResponse(NoContent))
  }
}

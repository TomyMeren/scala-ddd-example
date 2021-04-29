package tv.codely.scala_http_api.application.video.akkaHttp

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import tv.codely.scala_http_api.application.video.api.VideoCreator
import tv.codely.scala_http_api.module.video.domain.{VideoCategory, VideoDuration, VideoId, VideoTitle}

import scala.concurrent.Future
import scala.concurrent.duration.Duration

final class VideoPostController(creator: VideoCreator[Future]) {
  def post(id: String, title: String, duration: Duration, category: String): StandardRoute = {
    creator.create(VideoId(id), VideoTitle(title), VideoDuration(duration), VideoCategory(category))

    complete(HttpResponse(NoContent))
  }
}

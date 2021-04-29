package tv.codely.scala_http_api.application.video.akkaHttp.marshaller

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import tv.codely.scala_http_api.application.video.akkaHttp.marshaller.VideoAttributesJsonFormatMarshaller._
import tv.codely.scala_http_api.application.video.api.{Video, VideoCategory, VideoDuration, VideoId, VideoTitle}

object VideoJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val videoFormat: RootJsonFormat[Video] = jsonFormat(
    Video.apply(_: VideoId, _: VideoTitle, _: VideoDuration, _: VideoCategory),
    "id",
    "title",
    "duration_in_seconds",
    "category"
  )
}

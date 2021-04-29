package tv.codely.scala_http_api.effects.repositories.bbdd.slick

import slick.jdbc.{GetResult, SetParameter}
import tv.codely.scala_http_api.application.video.api.{Video, VideoCategory, VideoDuration, VideoId, VideoTitle}

object SlickConversionsVideo {

  implicit val getVideoResult: GetResult[Video] =
    GetResult(
      video =>
        Video(VideoId(video.nextString()),
              VideoTitle(video.nextString),
              VideoDuration(video.nextBigDecimal),
              VideoCategory(video.nextString))
    )

  implicit val videoIdSetter = SetParameter[VideoId] {
    case (videoId, params) => params.setString(videoId.value.toString)
  }

  implicit val videonTitleSetter = SetParameter[VideoTitle] {
    case (videotitle, params) => params.setString(videotitle.value)
  }

  implicit val videonDurationSetter = SetParameter[VideoDuration] {
    case (videoDuration, params) => params.setBigDecimal(videoDuration.value.toSeconds)
  }

  implicit val videonCategorySetter = SetParameter[VideoCategory] {
    case (videoCategory, params) => params.setString(videoCategory.toString)
  }
}

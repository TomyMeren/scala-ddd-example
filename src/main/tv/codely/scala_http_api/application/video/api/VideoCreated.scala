package tv.codely.scala_http_api.application.video.api

import tv.codely.scala_http_api.effects.bus.api.Message

object VideoCreated {
  def apply(id: String, title: String, duration: BigDecimal, category: String): VideoCreated = apply(
    VideoId(id),
    VideoTitle(title),
    VideoDuration(duration),
    VideoCategory(category)
  )

  def apply(video: Video): VideoCreated = apply(video.id, video.title, video.duration, video.category)
}

final case class VideoCreated(
    id: VideoId,
    title: VideoTitle,
    duration: VideoDuration,
    category: VideoCategory
) extends Message {
  override val subType: String = "video_created"
}

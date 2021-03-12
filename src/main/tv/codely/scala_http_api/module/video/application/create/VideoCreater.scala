package tv.codely.scala_http_api.module.video.application.create

import tv.codely.scala_http_api.module.video.domain.{VideoCategory, VideoDuration, VideoId, VideoTitle}

trait VideoCreater[P[_]] {
  def create(id: VideoId,
             title: VideoTitle,
             duration: VideoDuration,
             category: VideoCategory): P[Unit]
}

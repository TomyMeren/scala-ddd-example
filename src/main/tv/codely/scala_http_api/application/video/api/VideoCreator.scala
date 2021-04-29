package tv.codely.scala_http_api.application.video.api

import tv.codely.scala_http_api.module.video.domain.{VideoCategory, VideoDuration, VideoId, VideoTitle}

trait VideoCreator[P[_]] {
  def create(id: VideoId, title: VideoTitle, duration: VideoDuration, category: VideoCategory): P[Unit]
}

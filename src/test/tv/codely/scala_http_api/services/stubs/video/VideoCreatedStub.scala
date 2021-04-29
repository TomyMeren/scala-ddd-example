package tv.codely.scala_http_api.services.stubs.video

import tv.codely.scala_http_api.application.video.api.{Video, VideoCategory, VideoCreated, VideoDuration, VideoId, VideoTitle}

object VideoCreatedStub {
  def apply(
      id: VideoId = VideoIdStub.random,
      title: VideoTitle = VideoTitleStub.random,
      duration: VideoDuration = VideoDurationStub.random,
      category: VideoCategory = VideoCategoryStub.random
  ): VideoCreated = VideoCreated(id, title, duration, category)

  def apply(video: Video): VideoCreated = apply(video.id, video.title, video.duration, video.category)

  def random: VideoCreated = apply()
}

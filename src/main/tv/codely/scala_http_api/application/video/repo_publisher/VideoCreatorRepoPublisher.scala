package tv.codely.scala_http_api.application.video.repo_publisher

import cats.Apply
import cats.syntax.apply._
import tv.codely.scala_http_api.application.video.api.{Video, VideoCategory, VideoCreated, VideoCreator, VideoDuration, VideoId, VideoTitle}
import tv.codely.scala_http_api.effects.bus.api.MessagePublisher
import tv.codely.scala_http_api.effects.repositories.api.VideoRepository


final case class VideoCreatorRepoPublisher[P[_]]()(implicit repository: VideoRepository[P],
                                                 publisher: MessagePublisher[P],
                                                 F: Apply[P]) extends VideoCreator[P] {
  def create(id: VideoId, title: VideoTitle, duration: VideoDuration, category: VideoCategory)
  : P[Unit] = {
    val video = Video(id, title, duration, category)

    repository.save(video) *> publisher.publish(VideoCreated(video))
  }
}

package tv.codely.scala_http_api.application.video.repo_publisher

import cats.Apply
import cats.syntax.apply._
import tv.codely.scala_http_api.application.video.api.VideoCreator
import tv.codely.scala_http_api.module.shared.domain.MessagePublisher
import tv.codely.scala_http_api.module.video.domain._

final class VideoCreator[P[_]](repository: VideoRepository[P], publisher: MessagePublisher[P])
                              (implicit F: Apply[P]) extends VideoCreator[P] {
  def create(id: VideoId, title: VideoTitle, duration: VideoDuration, category: VideoCategory)
  : P[Unit] = {
    val video = Video(id, title, duration, category)

    repository.save(video) *> publisher.publish(VideoCreated(video))
  }
}

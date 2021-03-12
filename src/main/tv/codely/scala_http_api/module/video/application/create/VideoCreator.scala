package tv.codely.scala_http_api.module.video.application.create

import cats.Apply, cats.syntax.apply._
import tv.codely.scala_http_api.module.shared.domain.MessagePublisher
import tv.codely.scala_http_api.module.video.domain._

final class VideoCreator[P[_]](repository: VideoRepository[P], publisher: MessagePublisher[P])
                              (implicit F: Apply[P]) extends VideoCreater[P] {
  def create(id: VideoId, title: VideoTitle, duration: VideoDuration, category: VideoCategory)
  : P[Unit] = {
    val video = Video(id, title, duration, category)

    repository.save(video) *> publisher.publish(VideoCreated(video))
  }
}

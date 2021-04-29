package tv.codely.scala_http_api.application.video.repo_publisher

import tv.codely.scala_http_api.application.video.api.{Video, VideoSearcher}
import tv.codely.scala_http_api.effects.repositories.api.VideoRepository

final case class VideosSearcherRepo[P[_]]()(implicit
                                          repository: VideoRepository[P]) extends VideoSearcher[P] {
  def all(): P[Seq[Video]] = repository.all()
}

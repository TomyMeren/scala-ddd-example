package tv.codely.scala_http_api.application.video.repo_publisher

import tv.codely.scala_http_api.application.video.api.VideoSearcherRepo
import tv.codely.scala_http_api.module.video.domain.{Video, VideoRepository}

final class VideosSearcherRepo[P[_]](repository: VideoRepository[P]) extends VideoSearcherRepo[P] {
  def all(): P[Seq[Video]] = repository.all()
}

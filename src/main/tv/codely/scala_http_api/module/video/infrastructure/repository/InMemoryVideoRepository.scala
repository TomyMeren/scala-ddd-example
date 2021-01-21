package tv.codely.scala_http_api.module.video.infrastructure.repository

import tv.codely.scala_http_api.module.video.domain.{Video, VideoRepository}

import scala.concurrent.duration.DurationInt

final class InMemoryVideoRepository extends VideoRepository {
  private var videos:Seq[Video] = Seq()

  def all() = videos

  def save(video:Video):Unit =  videos = videos :+ video
}

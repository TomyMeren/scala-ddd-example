package tv.codely.scala_http_api.module.video.infrastructure.repository

import tv.codely.scala_http_api.module.video.domain.{Video, VideoRepository}

import scala.concurrent.duration.DurationInt

final class InMemoryVideoRepository extends VideoRepository {
  private var videos:Seq[Video] = Seq(
      Video(
        id = "3dfb19ee-260b-420a-b08c-ed58a7a07aee",
        title = "🎥 Scala FTW vol. 1",
        duration = 1.minute,
        category = "Screencast"
      ),
      Video(
        id = "7341b1fc-3d80-4f6a-bcde-4fef86b01f97",
        title = "🔝 Interview with Odersky",
        duration = 30.minutes,
        category = "Interview"
      )
    )

  def all() = videos

  def save(video:Video):Unit =  videos = videos :+ video
}
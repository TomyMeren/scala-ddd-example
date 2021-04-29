package tv.codely.scala_http_api.services.stubs.video

import tv.codely.scala_http_api.application.video.api.Video
import tv.codely.scala_http_api.services.stubs.SeqStub

import scala.concurrent.duration.Duration

object VideoStub {
  def apply(
      id: String = VideoIdStub.random.value.toString,
      title: String = VideoTitleStub.random.value.toString,
      duration: Duration = VideoDurationStub.random.value,
      category: String = VideoCategoryStub.random.toString,
  ): Video = Video(id, title, duration, category)

  def random: Video = apply()

  def randomSeq: Seq[Video] = SeqStub.randomOf(apply())
}

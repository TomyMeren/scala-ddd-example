package tv.codely.scala_http_api.module.video.domain

import tv.codely.scala_http_api.module.shared.domain.SeqStub

import scala.concurrent.duration.Duration

object VideoStub {
  def apply(
    id: String = VideoIdStub.random.value.toString,
    title: String = VideoTitleStub.random.value.toString,
    duration: Duration = VideoDurationStub.random.value,
    category: String = VideoCategoryStub.random.toString,
  ): Video = Video(id, title, duration, category)

  def random: Video = apply()


  def randomSeqVideo:Seq[Video] = SeqStub.randomOf(random)
}

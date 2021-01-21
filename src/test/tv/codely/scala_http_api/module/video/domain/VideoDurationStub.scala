package tv.codely.scala_http_api.module.video.domain

import tv.codely.scala_http_api.module.shared.domain.DurationStub

import scala.concurrent.duration.Duration

object VideoDurationStub {
  def apply(value: Duration): VideoDuration = VideoDuration(value)

  def random: VideoDuration = VideoDuration(DurationStub.random)
}

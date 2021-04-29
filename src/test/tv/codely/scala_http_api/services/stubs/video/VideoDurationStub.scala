package tv.codely.scala_http_api.services.stubs.video

import tv.codely.scala_http_api.application.video.api.VideoDuration
import tv.codely.scala_http_api.services.stubs.DurationStub

import scala.concurrent.duration.Duration

object VideoDurationStub {
  def apply(value: Duration): VideoDuration = VideoDuration(value)

  def random: VideoDuration = VideoDuration(DurationStub.random)
}

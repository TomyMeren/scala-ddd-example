package tv.codely.scala_http_api.services.stubs.video

import tv.codely.scala_http_api.application.video.api.VideoId
import tv.codely.scala_http_api.services.stubs.UuidStub

import java.util.UUID

object VideoIdStub {
  def apply(value: String): VideoId = VideoIdStub(UuidStub(value))

  def apply(value: UUID): VideoId = VideoId(value)

  def random: VideoId = VideoId(UuidStub.random)
}

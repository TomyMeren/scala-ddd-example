package tv.codely.scala_http_api.services.stubs.video

import tv.codely.scala_http_api.application.video.api.VideoCategory
import tv.codely.scala_http_api.services.stubs.IntStub

object VideoCategoryStub {
  private val categories = Seq("Screencast", "Interview")

  def apply(value: String): VideoCategory = VideoCategory(value)

  def random: VideoCategory = VideoCategory(categories(IntStub.randomBetween(min = 0, max = categories.size - 1)))
}

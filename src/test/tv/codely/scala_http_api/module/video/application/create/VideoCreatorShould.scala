package tv.codely.scala_http_api.module.video.application.create

import tv.codely.scala_http_api.module.video.domain.VideoStub
import tv.codely.scala_http_api.module.video.infrastructure.VideoUnitTestCase

final class VideoCreatorShould extends VideoUnitTestCase {
  private val creator = new VideoCreator(repository)

  "VideoCreator" should {
    "create Videos" in {
      val existingVideo = VideoStub.random

      shouldSaveVideo(existingVideo)

      creator.create(existingVideo.id, existingVideo.title, existingVideo.duration, existingVideo.category) should be()

    }
  }
}

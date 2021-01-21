package tv.codely.scala_http_api.module.video.infrastructure.repository

import tv.codely.scala_http_api.module.video.domain.VideoStub
import tv.codely.scala_http_api.module.video.infrastructure.VideoIntegrationTestCase

protected[video] final class InMemoryVideoRepositoryTest extends VideoIntegrationTestCase {
  "Video repository test" should {
    "insert and search de test" in {

      val randomVideo = VideoStub.random
      val anotherRandomVideo = VideoStub.random

      val existingVideos = Seq(randomVideo, anotherRandomVideo)

      repository.save(randomVideo)
      repository.save(anotherRandomVideo)

      repository.all() should be(existingVideos)
    }
  }
}

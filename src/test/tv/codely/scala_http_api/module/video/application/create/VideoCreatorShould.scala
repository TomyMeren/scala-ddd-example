package tv.codely.scala_http_api.module.video.application.create

import tv.codely.scala_http_api.module.video.domain.VideoStub
import tv.codely.scala_http_api.module.video.infrastructure.VideoRepositoryMock
import tv.codely.scala_http_api.module.UnitTestCase

final class VideoCreatorShould extends UnitTestCase with VideoRepositoryMock {
  private val creator = new VideoCreator(repository)

    "save a Videos" in {
      val existingVideo = VideoStub.random

      repositoryShouldSave(existingVideo)

      creator.create(existingVideo.id, existingVideo.title, existingVideo.duration, existingVideo.category) should be()

    }
  }


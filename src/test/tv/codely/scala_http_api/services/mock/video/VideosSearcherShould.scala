package tv.codely.scala_http_api.services.mock.video

import tv.codely.scala_http_api.application.video.repo_publisher.VideosSearcherRepo
import tv.codely.scala_http_api.effects.repositories.mock.VideoRepositoryMock
import tv.codely.scala_http_api.services.mock.UnitTestCase
import tv.codely.scala_http_api.services.stubs.video.VideoStub

final class VideosSearcherShould extends UnitTestCase with VideoRepositoryMock {
  private val searcher = VideosSearcherRepo()(repository)

  "search all existing videos" in {
    val existingVideo        = VideoStub.random
    val anotherExistingVideo = VideoStub.random
    val existingVideos       = Seq(existingVideo, anotherExistingVideo)

    repositoryShouldFind(existingVideos)

    searcher.all().futureValue shouldBe existingVideos
  }
}

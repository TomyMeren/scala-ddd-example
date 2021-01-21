package tv.codely.scala_http_api.module.video.application.search

import tv.codely.scala_http_api.module.video.domain.VideoStub
import tv.codely.scala_http_api.module.video.infrastructure.VideoUnitTestCase

final class VideoSearcherTest extends VideoUnitTestCase {
  private val searcher = new VideoSearcher(repository)

  "Video Creator" should {
    "Save a  videos" in {
      val existingVideo = VideoStub.random
      val anotherExistingVideos = VideoStub.random
      val existingVideos = Seq(existingVideo, anotherExistingVideos)

      shouldSearchAllVideo(existingVideos)

      searcher.all() should be (existingVideos)

    }
  }
}
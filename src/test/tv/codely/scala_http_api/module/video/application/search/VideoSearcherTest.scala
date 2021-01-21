package tv.codely.scala_http_api.module.video.application.create

import tv.codely.scala_http_api.module.video.VideoUnitTestCase
import tv.codely.scala_http_api.module.video.application.search.VideoSearcher
import tv.codely.scala_http_api.module.video.infrastructure.stub.VideoStub

final class VideoSearcherTest extends VideoUnitTestCase {
  private val searcher = new VideoSearcher(repository)

  "Video Searcher" should {
    "search all existing videos" in {
      val existingVideo = VideoStub.random
      val anotherExistingVideos = VideoStub.random
      val existingVideos = Seq(existingVideo, anotherExistingVideos)
      
      shouldSearchAllVideo(existingVideos)

      searcher.all() should be (existingVideos)

    }
  }
}

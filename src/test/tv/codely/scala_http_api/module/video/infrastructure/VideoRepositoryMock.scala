package tv.codely.scala_http_api.module.video.infrastructure

import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.video.domain.{Video, VideoRepository}

protected[video] trait VideoUnitTestCase extends UnitTestCase {

  protected val repository: VideoRepository = mock[VideoRepository]

  protected def shouldSearchAllVideo(videos: Seq[Video]): Unit = {
    (repository.all _)
      .expects()
      .returning(videos)
  }

  protected def shouldSaveVideo(video: Video): Unit = {
    (repository.save _)
      .expects(video)
      .returning(())
  }
}

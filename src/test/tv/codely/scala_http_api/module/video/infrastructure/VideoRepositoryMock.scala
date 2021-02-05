package tv.codely.scala_http_api.module.video.infrastructure

import org.scalamock.scalatest.MockFactory
import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.video.domain.{Video, VideoRepository}

import scala.concurrent.Future

protected[video] trait VideoRepositoryMock extends UnitTestCase with MockFactory {
  this:UnitTestCase =>

  protected val repository: VideoRepository = mock[VideoRepository]

  protected def shouldSearchAllVideo(videos: Seq[Video]): Unit = {
    (repository.all _)
      .expects()
      .returning(Future.successful(videos))
  }

  protected def repositoryShouldSave(video: Video): Unit = {
    (repository.save _)
      .expects(video)
      .returning(Future.unit)
  }
}

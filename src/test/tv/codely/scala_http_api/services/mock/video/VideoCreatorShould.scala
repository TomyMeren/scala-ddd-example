package tv.codely.scala_http_api
package services.mock.video

import cats.instances.future._
import tv.codely.scala_http_api.application.video.repo_publisher.VideoCreatorRepoPublisher
import tv.codely.scala_http_api.effects.bus.mock.MessagePublisherMock
import tv.codely.scala_http_api.effects.repositories.mock.VideoRepositoryMock
import tv.codely.scala_http_api.services.mock.UnitTestCase
import tv.codely.scala_http_api.services.stubs.video.{VideoCreatedStub, VideoStub}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class VideoCreatorShould extends UnitTestCase with VideoRepositoryMock with MessagePublisherMock {
  private val creator = new VideoCreatorRepoPublisher[Future]()(repository, messagePublisher,implicitly)

  "save a video" in {
    val video        = VideoStub.random
    val videoCreated = VideoCreatedStub(video)

    repositoryShouldSave(video)

    publisherShouldPublish(videoCreated)

    creator.create(video.id, video.title, video.duration, video.category).map(_.shouldBe(()))
  }
}

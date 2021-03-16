package tv.codely.scala_http_api.module.video.infrastructure.dependency_injection

import cats.Id
import cats.effect.IO
import cats.implicits.catsStdInstancesForFuture
import tv.codely.scala_http_api._
import tv.codely.scala_http_api.module.shared.domain.MessagePublisher
import tv.codely.scala_http_api.module.shared.domain.MessagePublisher.fromToMp
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.scala_http_api.module.video.application.create.VideoCreator
import tv.codely.scala_http_api.module.video.application.search.VideosSearcher
import tv.codely.scala_http_api.module.video.domain.VideoRepository
import tv.codely.scala_http_api.module.video.domain.VideoRepository.toQ
import tv.codely.scala_http_api.module.video.infrastructure.repository.DoobieMySqlVideoRepository

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class VideoModuleDependencyContainer(
    doobieDbConnection: DoobieDbConnection[IO],
    messagePublisher: MessagePublisher[Id]
) {
  val repository: VideoRepository[Future] = toQ(new DoobieMySqlVideoRepository(doobieDbConnection),fromIOToFuture)

  val videosSearcher: VideosSearcher[Future] = new VideosSearcher(repository)
  val videoCreator: VideoCreator[Future]     = new VideoCreator[Future](repository, messagePublisher)
}

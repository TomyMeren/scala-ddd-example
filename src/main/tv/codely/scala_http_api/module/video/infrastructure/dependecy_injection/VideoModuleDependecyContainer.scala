package tv.codely.scala_http_api.module.video.infrastructure.dependecy_injection

import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.scala_http_api.module.video.application.create.VideoCreator
import tv.codely.scala_http_api.module.video.application.search.VideoSearcher
import tv.codely.scala_http_api.module.video.infrastructure.repository.DoobieMySqlVideoRepository

import scala.concurrent.ExecutionContext.Implicits.global

final class VideoModuleDependecyContainer(doobieDbConnection:DoobieDbConnection) {

  val repo = new DoobieMySqlVideoRepository(doobieDbConnection)

  val videoSearcher = new VideoSearcher(repo)

  val videoCreator = new VideoCreator(repo)
}

package tv.codely.scala_http_api.module.user.infrastructure.dependency_injection

import cats.Id
import cats.implicits.catsStdInstancesForFuture
import tv.codely.scala_http_api.module.shared.domain.MessagePublisher
import tv.codely.scala_http_api.module.shared.domain.MessagePublisher._
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.scala_http_api.module.user.application.register.UserRegistrar
import tv.codely.scala_http_api.module.user.application.search.UsersSearcher
import tv.codely.scala_http_api.module.user.domain.UserRepository
import tv.codely.scala_http_api.module.user.infrastructure.repository.DoobieMySqlUserRepository


import scala.concurrent.{ExecutionContext, Future}

final class UserModuleDependencyContainer(
    doobieDbConnection: DoobieDbConnection,
    messagePublisher: MessagePublisher[Id]
)(implicit ex: ExecutionContext) {
  val repository: UserRepository[Future] = new DoobieMySqlUserRepository(doobieDbConnection)

  val usersSearcher:UsersSearcher[Future] = new UsersSearcher(repository)
  val userRegistrar:UserRegistrar[Future] = new UserRegistrar[Future](repository, messagePublisher)
}

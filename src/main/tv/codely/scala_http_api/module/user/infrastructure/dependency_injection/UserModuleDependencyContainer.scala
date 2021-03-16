package tv.codely.scala_http_api.module.user.infrastructure.dependency_injection

import cats.Id
import cats.effect.IO
import cats.implicits.catsStdInstancesForFuture
import tv.codely.scala_http_api.module.shared.domain.MessagePublisher
import tv.codely.scala_http_api.module.shared.domain.MessagePublisher._
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.scala_http_api.module.user.application.register.UserRegistrar
import tv.codely.scala_http_api.module.user.application.search.UsersSearcher
import tv.codely.scala_http_api.module.user.domain.UserRepository
import tv.codely.scala_http_api.module.user.infrastructure.repository.DoobieMySqlUserRepository
import tv.codely.scala_http_api.module.user.domain.UserRepository.toQ
import tv.codely.scala_http_api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class UserModuleDependencyContainer(
    doobieDbConnection: DoobieDbConnection[IO],
    messagePublisher: MessagePublisher[Id]){
  val repository: UserRepository[Future] = toQ(new DoobieMySqlUserRepository(doobieDbConnection),fromIOToFuture)

  val usersSearcher:UsersSearcher[Future] = new UsersSearcher[Future](repository)
  val userRegistrar:UserRegistrar[Future] = new UserRegistrar(repository, messagePublisher)
}

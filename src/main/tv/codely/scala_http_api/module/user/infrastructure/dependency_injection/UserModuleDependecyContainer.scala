package tv.codely.scala_http_api.module.user.infrastructure.dependency_injection

import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.scala_http_api.module.user.application.create.UserCreator
import tv.codely.scala_http_api.module.user.application.search.UserSearcher
import tv.codely.scala_http_api.module.user.infrastructure.repository.DoobieMySqlUserRepository

import scala.concurrent.ExecutionContext.Implicits.global

final class UserModuleDependecyContainer(doobieDbConnection:DoobieDbConnection) {

  val repo = new DoobieMySqlUserRepository(doobieDbConnection)

  val userSearcher = new UserSearcher(repo)

  val userCreater = new UserCreator(repo)

}

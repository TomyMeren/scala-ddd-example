package tv.codely.scala_http_api.module.user.infrastructure.dependency_injection

import tv.codely.scala_http_api.module.user.application.UserSearcher
import tv.codely.scala_http_api.module.user.infrastructure.repository.InMemoryUserRepository

final class UserModuleDependecyContainer {

  val repo = new InMemoryUserRepository

  val userSearcher = new UserSearcher(repo)

}

package tv.codely.scala_http_api.module.user.infrastructure.dependency_injection

import tv.codely.scala_http_api.module.user.application.UserSearcher

final class UserModuleDependecyContainer {

  val userSearcher = new UserSearcher

}

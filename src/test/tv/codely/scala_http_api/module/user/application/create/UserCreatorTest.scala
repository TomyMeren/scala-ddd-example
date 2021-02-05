package tv.codely.scala_http_api.module.user.application.create

import tv.codely.scala_http_api.module.user.domain.UserStub
import tv.codely.scala_http_api.module.user.infrastructure.UserRepositoryMock

final class UserCreatorTest extends UserRepositoryMock {
  private val creater = new UserCreator(repository)

  "create a User" in {
    val user = UserStub.random
    repositoryShouldSave(user)

    creater.create(user.id, user.name) should be()
  }
}

package tv.codely.scala_http_api.module.user.application.search

import tv.codely.scala_http_api.module.user.domain.UserStub
import tv.codely.scala_http_api.module.user.infrastructure.UserRepositoryMock

final class UserSearcherTest extends UserRepositoryMock {
  private val searcher = new UserSearcher(repository)

  "User Searcher" should {
    "search all existing users" in {
      val existingUser = UserStub.random
      val anotherExistingUser = UserStub.random
      val existingUsers = Seq(existingUser, anotherExistingUser)

      shouldSearchAllUsers(existingUsers)

      searcher.all().futureValue should be (existingUsers)

    }
  }
}

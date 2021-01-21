package tv.codely.scala_http_api.module.user.infrastructure.repository

import tv.codely.scala_http_api.module.user.domain.UserStub
import tv.codely.scala_http_api.module.user.infrastructure.UserIntegrationTestCase

final class InMemoryUserRepositoryTest extends UserIntegrationTestCase {

  "User repository Searcher" should {
    "search all existing users" in {

      val expectedUser = Seq(
        UserStub(id = "deacd129-d419-4552-9bfc-0723c3c4f56a", name = "Edufasio"),
        UserStub(id = "b62f767f-7160-4405-a4af-39ebb3635c17", name = "Edonisio")
      )

      repository.all() should be(expectedUser)
    }
  }
}

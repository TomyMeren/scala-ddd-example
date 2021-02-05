package tv.codely.scala_http_api.module.user.infrastructure.repository

import doobie.implicits._
import tv.codely.scala_http_api.module.user.domain.UserStub.randomSeqUser
import tv.codely.scala_http_api.module.user.infrastructure.UserIntegrationTestCase

protected[user] final class DoobieMySqlUserRepositoryTest extends UserIntegrationTestCase {

  protected def cleanTable =
    sql"TRUNCATE TABLE users".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    cleanTable
  }

  "return users is empty" in {
    repository.all.futureValue shouldBe Seq.empty
  }

  "search all existing users" in {
    val expectedUser = randomSeqUser
    expectedUser.map(repository.save(_).futureValue)
    repository.all().futureValue shouldBe expectedUser
  }
}

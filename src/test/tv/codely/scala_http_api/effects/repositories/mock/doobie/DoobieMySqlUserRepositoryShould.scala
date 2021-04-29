package tv.codely.scala_http_api
package effects.repositories.mock.doobie

import doobie.implicits._
import org.scalatest.BeforeAndAfterEach
import tv.codely.scala_http_api.application.user.api.User
import tv.codely.scala_http_api.services.stubs.user.UserStub


final class DoobieMySqlUserRepositoryShould extends UserIntegrationTestCase with BeforeAndAfterEach {
  private def cleanUsersTable() =
    sql"TRUNCATE TABLE users".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    cleanUsersTable()
  }

  "return an empty sequence if there're no users" in {
    repository.all().unsafeToFuture().futureValue shouldBe Seq.empty
    //UserRepository.toQ(repository,fromIOToFuture).all().futureValue shouldBe Seq.empty
  }

  //TODO: hemos tenido que poner unsafeToFuture() y  .unsafeToFuture().futureValue

  "search all existing users" in {
    val users: Seq[User] = UserStub.randomSeq

    users.foreach(u => repository.save(u).unsafeToFuture)

    repository.all().unsafeToFuture.futureValue  shouldBe users
  }
}

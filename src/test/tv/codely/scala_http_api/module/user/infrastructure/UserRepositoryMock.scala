package tv.codely.scala_http_api.module.user.infrastructure

import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.user.domain.{User, UserRepository}

import scala.concurrent.Future

protected[user] trait UserUnitTestCase extends UnitTestCase {
  protected val repository: UserRepository = mock[UserRepository]

  protected def shouldSearchAllUsers(users: Seq[User]): Unit =
    (repository.all _)
      .expects()
      .returning(users)

  protected def repositoryShouldSave(user: User): Unit = {
    (repository.save _)
      .expects(user)
      .returning(Future.unit)
  }
}

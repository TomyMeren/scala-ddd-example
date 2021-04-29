package tv.codely.scala_http_api
package services.mock.user

import tv.codely.scala_http_api.application.user.repo_publisher.UserRegisterRepoPublisher
import tv.codely.scala_http_api.effects.bus.mock.MessagePublisherMock
import tv.codely.scala_http_api.effects.repositories.mock.UserRepositoryMock
import tv.codely.scala_http_api.services.mock.UnitTestCase
import tv.codely.scala_http_api.services.stubs.user.{UserRegisteredStub, UserStub}
import cats.instances.future._

import scala.concurrent.Future, scala.concurrent.ExecutionContext.Implicits.global

final class UserRegistrarShould extends UnitTestCase with UserRepositoryMock with MessagePublisherMock {
  private val registrar = new UserRegisterRepoPublisher[Future]()(repository, messagePublisher,implicitly)

  "register a user" in {
    val user           = UserStub.random
    val userRegistered = UserRegisteredStub(user)

    repositoryShouldSave(user)

    publisherShouldPublish(userRegistered)

    registrar.register(user.id, user.name).map(_.shouldBe(()))
  }
}

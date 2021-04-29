package tv.codely.scala_http_api.application.user.repo_publisher

import cats.Apply
import cats.syntax.apply._
import tv.codely.scala_http_api.application.user.api.{User, UserId, UserName, UserRegister, UserRegistered}
import tv.codely.scala_http_api.effects.bus.api.MessagePublisher
import tv.codely.scala_http_api.effects.repositories.api.UserRepository

final case class UserRegisterRepoPublisher[P[_]]()(implicit repository: UserRepository[P],
                                                 publisher: MessagePublisher[P],
                                                 F: Apply[P])
    extends UserRegister[P] {
  def register(id: UserId, name: UserName): P[Unit] = {
    val user = User(id, name)

    (repository.save(user): P[Unit]) *> publisher.publish(UserRegistered(user))
  }
}

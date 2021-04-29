package tv.codely.scala_http_api.application.user.repo_publisher

import cats.Apply
import cats.syntax.apply._
import tv.codely.scala_http_api.module.shared.domain.MessagePublisher
import tv.codely.scala_http_api.module.user.application.register.UserRegister
import tv.codely.scala_http_api.module.user.domain._

final class UserRegistrar[P[_]](repository: UserRepository[P], publisher: MessagePublisher[P])
                               (implicit F: Apply[P]) extends UserRegister[P] {
  def register(id: UserId, name: UserName): P[Unit] = {
    val user = User(id, name)

    (repository.save(user):P[Unit]) *> publisher.publish(UserRegistered(user))
  }
}

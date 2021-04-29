package tv.codely.scala_http_api.application.user.repo_publisher

import tv.codely.scala_http_api.application.user.api.{User, UsersSearcher}
import tv.codely.scala_http_api.effects.repositories.api.UserRepository

final case class UsersSearcherRepo[P[_]]()(implicit repository: UserRepository[P])
  extends UsersSearcher[P] {
  def all(): P[Seq[User]] = repository.all()
}

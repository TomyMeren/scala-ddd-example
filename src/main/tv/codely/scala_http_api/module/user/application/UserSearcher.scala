package tv.codely.scala_http_api.module.user.application

import tv.codely.scala_http_api.module.user.domain.{User, UserRepository}

final class UserSearcher(repository:UserRepository) {
  def all(): Seq[User] = repository.all()

}

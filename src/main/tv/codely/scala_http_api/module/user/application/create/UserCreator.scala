package tv.codely.scala_http_api.module.user.application.create

import tv.codely.scala_http_api.module.user.domain.{User, UserId, UserName, UserRepository}

final class UserCreator(repository: UserRepository) {
  def create(userId: UserId, userName: UserName): Unit = {
    val newUser: User = User(userId, userName)
    repository.save(newUser)
  }
}

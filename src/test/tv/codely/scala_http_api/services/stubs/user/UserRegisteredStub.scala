package tv.codely.scala_http_api.services.stubs.user

import tv.codely.scala_http_api.application.user.api.{User, UserId, UserName, UserRegistered}

object UserRegisteredStub {
  def apply(
      id: UserId = UserIdStub.random,
      name: UserName = UserNameStub.random
  ): UserRegistered = UserRegistered(id, name)

  def apply(user: User): UserRegistered = apply(user.id, user.name)

  def random: UserRegistered = apply()
}

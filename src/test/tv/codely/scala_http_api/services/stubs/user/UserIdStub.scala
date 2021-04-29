package tv.codely.scala_http_api.services.stubs.user

import tv.codely.scala_http_api.application.user.api.UserId
import tv.codely.scala_http_api.services.stubs.UuidStub

import java.util.UUID

object UserIdStub {
  def apply(value: String): UserId = UserIdStub(UuidStub(value))

  def apply(value: UUID): UserId = UserId(value)

  def random: UserId = UserId(UuidStub.random)
}

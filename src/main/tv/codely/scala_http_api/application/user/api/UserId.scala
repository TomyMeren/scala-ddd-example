package tv.codely.scala_http_api.application.user.api

import slick.lifted.MappedTo
import java.util.UUID

object UserId {
  def apply(value: String): UserId = UserId(UUID.fromString(value))

}

case class UserId(value: UUID) extends MappedTo[UUID]

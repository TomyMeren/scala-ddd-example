package tv.codely.scala_http_api.module.courses.domain

import java.util.UUID

import tv.codely.scala_http_api.domain.UuidStub

object CourseIdStub {
  def apply(id: String) = CourseId(UuidStub(id))

  def apply(id:UUID) = CourseId(id)

  def random = CourseId(UuidStub.random)
}

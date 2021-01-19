package tv.codely.scala_http_api.module.courses.infrastructure.stub

import tv.codely.scala_http_api.module.courses.domain.CourseId

object CourseIdStub {
  def apply(id: String) = CourseId(id)
}

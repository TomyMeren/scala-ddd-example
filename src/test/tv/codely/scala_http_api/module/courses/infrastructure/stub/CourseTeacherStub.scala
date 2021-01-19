package tv.codely.scala_http_api.module.courses.infrastructure.stub

import tv.codely.scala_http_api.module.courses.domain.CourseTeacherName

object CourseTeacherStub {
  def apply(id: String) = CourseTeacherName(id)
}

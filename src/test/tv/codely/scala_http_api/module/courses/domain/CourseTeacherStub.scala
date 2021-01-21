package tv.codely.scala_http_api.module.courses.domain

import tv.codely.scala_http_api.domain.StringStub

object CourseTeacherStub {
  def apply(name: String) = CourseTeacherName(name)

  def random = CourseTeacherName(StringStub.random(10))
}

package tv.codely.scala_http_api.module.courses.infrastructure.stub

import tv.codely.scala_http_api.module.courses.domain.Course

object CourseStub {
  def apply(id:String, teacher:String) = Course(id, teacher)
}

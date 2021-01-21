package tv.codely.scala_http_api.module.courses.domain

object CourseStub {
  def apply(id: String, teacher: String) = Course(id, teacher)

  def random = Course(CourseIdStub.random.value.toString, CourseTeacherStub.random.value)
}

package tv.codely.scala_http_api.module.courses.domain

object CourseStub {
  def apply(id: String, teacher: String, listVideoId:Vector[String]) = Course(id, teacher, listVideoId)

  def random = Course(CourseIdStub.random.value.toString, CourseTeacherStub.random.value, CourseVideoIdListStub.random.value.map(_.value.toString))
}

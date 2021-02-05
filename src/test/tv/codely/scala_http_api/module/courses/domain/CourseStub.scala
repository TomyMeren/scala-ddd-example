package tv.codely.scala_http_api.module.courses.domain

import tv.codely.scala_http_api.module.shared.domain.SeqStub.randomOf

object CourseStub {
  def apply(id: String, teacher: String) = Course(id, teacher)

  def random = Course(CourseIdStub.random.value.toString, CourseTeacherStub.random.value)

  def randomSeqCourse:Seq[Course] = randomOf(random)
}

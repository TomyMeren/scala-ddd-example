package tv.codely.scala_http_api.module.courses.domain

object Course {
  def apply(id: String, teacher: String): Course =
    Course(CourseId(id), CourseTeacherName(teacher))
}

case class Course(id: CourseId, teacher: CourseTeacherName)

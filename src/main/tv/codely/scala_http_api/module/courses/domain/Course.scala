package tv.codely.scala_http_api.module.courses.domain

object Course {
  def apply(id: String, teacher: String, videoIdLists: Vector[String]): Course =
    Course(CourseId(id), CourseTeacherName(teacher), CourseVideoIdLists(videoIdLists))
}

case class Course(id: CourseId, teacher: CourseTeacherName, videoIdLists:CourseVideoIdLists)

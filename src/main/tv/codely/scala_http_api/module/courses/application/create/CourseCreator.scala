package tv.codely.scala_http_api.module.courses.application.create

import tv.codely.scala_http_api.module.courses.domain._

final class CourseCreator(repository: CourseRepository) {
  def create(id: CourseId, teacher: CourseTeacherName, videoID: CourseVideoIdLists):Unit = {
    val curso = Course(id, teacher,videoID)

    repository.save(curso)
  }
}

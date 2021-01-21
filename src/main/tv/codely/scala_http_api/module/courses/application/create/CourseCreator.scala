package tv.codely.scala_http_api.module.courses.application.create

import tv.codely.scala_http_api.module.courses.domain.{Course, CourseId, CourseRepository, CourseTeacherName}

final class CourseCreator(repository: CourseRepository) {
  def create(id: CourseId, teacher: CourseTeacherName):Unit = {
    val curso = Course(id, teacher)

    repository.save(curso)
  }
}
package tv.codely.scala_http_api.module.courses.infraestructure.repository

import tv.codely.scala_http_api.module.courses.domain.{Course, CourseRepository}

final class InMemoryCourseRepository extends CourseRepository {

  private var cursos = Seq(
    Course(
      id = "7341b1fc-3d80-4f6a-bcde-4fef86b01f95",
      teacher = "Nacho Vidal"
    ),
    Course(
      id = "7341b1fc-3d80-4f6a-bcde-4fef86b01f93",
      teacher = "Jordi NP"
    )
  )

  def all() = cursos

  def save(curso:Course):Unit = cursos = curso +: cursos

}

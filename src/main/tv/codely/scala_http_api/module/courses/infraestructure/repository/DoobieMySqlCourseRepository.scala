package tv.codely.scala_http_api.module.courses.infraestructure.repository

import tv.codely.scala_http_api.module.courses.domain.{Course, CourseRepository}

final class InMemoryCourseRepository extends CourseRepository {

  private var cursos:Seq[Course] = Seq()

  def all() = cursos

  def save(curso:Course):Unit = cursos = cursos :+ curso

}

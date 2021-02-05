package tv.codely.scala_http_api.module.courses.infraestructure.dependency_injection

import tv.codely.scala_http_api.module.courses.application.create.CourseCreator
import tv.codely.scala_http_api.module.courses.application.search.CourseSearcher
import tv.codely.scala_http_api.module.courses.infraestructure.repository.DoobieMySqlCourseRepository
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext.Implicits.global

final class CourseModuleDependencyContainer(doobieDbConnection:DoobieDbConnection) {

  val repo = new DoobieMySqlCourseRepository(doobieDbConnection)

  val courseSearcher = new CourseSearcher(repo)
  val courseCreator = new CourseCreator(repo)
}

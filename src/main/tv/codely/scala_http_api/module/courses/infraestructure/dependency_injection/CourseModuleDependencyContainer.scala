package tv.codely.scala_http_api.module.courses.infraestructure.dependency_injection

import tv.codely.scala_http_api.module.courses.application.create.CourseCreator
import tv.codely.scala_http_api.module.courses.application.search.CourseSearcher
import tv.codely.scala_http_api.module.courses.infraestructure.repository.InMemoryCourseRepository

final class CourseModuleDependencyContainer {

  val repo = new InMemoryCourseRepository

  val courseSearcher = new CourseSearcher(repo)
  val courseCreator = new CourseCreator(repo)
}

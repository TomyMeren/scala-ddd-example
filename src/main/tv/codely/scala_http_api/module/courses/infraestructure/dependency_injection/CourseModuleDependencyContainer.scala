package tv.codely.scala_http_api.module.courses.infraestructure.dependency_injection

import tv.codely.scala_http_api.module.courses.application.CourseSearcher

final class CourseModuleDependencyContainer {

  val courseSearcher = new CourseSearcher
}

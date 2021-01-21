package tv.codely.scala_http_api.module.courses.application.search

import tv.codely.scala_http_api.module.courses.domain.CourseRepository

final class CourseSearcher(repository: CourseRepository) {

  def all() = repository.all()
}

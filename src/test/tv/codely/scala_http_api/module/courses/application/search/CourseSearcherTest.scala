package tv.codely.scala_http_api.module.courses.application.search

import tv.codely.scala_http_api.module.courses.domain.{Course, CourseStub}
import tv.codely.scala_http_api.module.courses.infrastructure.CourseRepositoryMock

final class CourseSearcherTest extends CourseRepositoryMock {
  val searcher = new CourseSearcher(repository)

  "Obtain all courses" should {
    "Obtain all courses" in {
      val course:Course = CourseStub.random
      val anotherCourse = CourseStub.random
      val courses = Seq(course, anotherCourse)

      shouldSearchAllCourses(courses)

      searcher.all().futureValue should be(courses)
    }
  }
}

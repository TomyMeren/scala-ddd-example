package tv.codely.scala_http_api.module.courses.infrastructure

import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.courses.domain.{Course, CourseRepository}

trait CourseUnitTestCase extends UnitTestCase with mock {

  val repository: CourseRepository = mock[CourseRepository]

  protected def shouldSearchAllCourses(courses: Seq[Course]): Unit = {
    (repository.all _)
      .expects()
      .returning(courses)
  }

  protected def shouldCreateVideo(course: Course): Unit = {
    (repository.save _)
      .expects(course)
      .returning(())
  }
}

package tv.codely.scala_http_api.module.courses.infrastructure

import org.scalamock.scalatest.MockFactory
import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.courses.domain.{Course, CourseRepository}

import scala.concurrent.Future

trait CourseRepositoryMock extends UnitTestCase with MockFactory {

  val repository: CourseRepository = mock[CourseRepository]

  protected def shouldSearchAllCourses(courses: Seq[Course]): Unit = {
    (repository.all _)
      .expects()
      .returning(Future.successful(courses))
  }

  protected def shouldCreateVideo(course: Course): Unit = {
    (repository.save _)
      .expects(course)
      .returning(Future.unit)
  }
}

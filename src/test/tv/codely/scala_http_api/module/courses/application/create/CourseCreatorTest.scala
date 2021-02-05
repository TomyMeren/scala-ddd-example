package tv.codely.scala_http_api.module.courses.application.create

import tv.codely.scala_http_api.module.courses.domain.{Course, CourseStub}
import tv.codely.scala_http_api.module.courses.infrastructure.CourseRepositoryMock

final class CourseCreatorTest extends CourseRepositoryMock {

  val creater = new CourseCreator(repository)

  "Crete course" should {
    "Obtain all courses" in {
      val course:Course = CourseStub.random

      shouldCreateVideo(course)

      creater.create(course.id,course.teacher) should be()

    }
  }
}

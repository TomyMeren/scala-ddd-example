package tv.codely.scala_http_api.module.courses.infrastructure.repository

import tv.codely.scala_http_api.module.courses.domain.CourseStub
import tv.codely.scala_http_api.module.courses.infrastructure.CourseIntegrationTestCase

protected[courses] final class InMemoryCourseRepositoryTest extends CourseIntegrationTestCase {
  "test de integracion de users" should {
    "creamos 2 usuarios y verificamos que existen" in {
      val existingCourse = CourseStub.random
      val anotherCourse = CourseStub.random

      val listCourses =Seq(existingCourse,anotherCourse)

      repository.save(existingCourse)
      repository.save(anotherCourse)

      println(repository.all())

      repository.all() should be(listCourses)
    }
  }
}

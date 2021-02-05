package tv.codely.scala_http_api.module.courses.infrastructure.repository

import doobie.implicits._
import doobie.implicits.toSqlInterpolator
import tv.codely.scala_http_api.module.courses.domain.CourseStub.randomSeqCourse
import tv.codely.scala_http_api.module.courses.infrastructure.CourseIntegrationTestCase

protected[courses] final class DoobieMySqlCourseRepositoryTest extends CourseIntegrationTestCase {

  protected def cleanTable =
    sql"TRUNCATE TABLE courses".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    cleanTable
  }

  "La tabla courses esta vacia repo esta vacio" in {
    repository.all().futureValue shouldBe Seq.empty
  }

  "creamos 2 usuarios y verificamos que existen" in {

    val listCourses = randomSeqCourse

    listCourses.foreach(repository.save(_).futureValue)

    repository.all().futureValue shouldBe listCourses
  }
}

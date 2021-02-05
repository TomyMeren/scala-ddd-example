package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import spray.json._
import doobie.implicits._
import tv.codely.scala_http_api.module.courses.domain.CourseStub
import tv.codely.scala_http_api.module.courses.infrastructure.marshaller.CourseJsValueMarshaller

final class CourseSpec extends AcceptanceSpec {

  def cleanTable() =
    sql"TRUNCATE TABLE courses".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  "post a course" in {
    post("/courses",
    """ {
        | "id": "7341b1fc-3d80-4f6a-bcde-4fef86b01f95",
        | "teacher": "Nacho Vidal"
        |}
        |""".stripMargin) {
      status shouldBe StatusCodes.NoContent
    }
  }

  "return all courses" in {
    cleanTable()

    val repository = courseContainer.repo
    val randomSeqUser = CourseStub.randomSeqCourse

    randomSeqUser.foreach(repository.save(_).futureValue)

/*      val expectedCourses = Seq(
        CourseStub(
          id = "7341b1fc-3d80-4f6a-bcde-4fef86b01f95",
          teacher = "Nacho Vidal"
        )
      )*/

    get("/courses") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe CourseJsValueMarshaller.marshall(randomSeqUser)
    }
  }
}

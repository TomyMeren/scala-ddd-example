package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import spray.json._
import tv.codely.scala_http_api.module.courses.domain.CourseStub
import tv.codely.scala_http_api.module.courses.infrastructure.marshaller.CourseMarshaller

final class CourseSpec extends AcceptanceSpec {

  "post a course" in {
    post("/courses",
    """ {
        | "id": "7341b1fc-3d80-4f6a-bcde-4fef86b01f95",
        | "teacher": "Nacho Vidal",
        | "listVideoId": ["7341b1fc-3d80-4f6a-bcde-4fef86b01f91","7341b1fc-3d80-4f6a-bcde-4fef86b01f93","7341b1fc-3d80-4f6a-bcde-4fef86b01f92"]
        |}
        |""".stripMargin) {
      status shouldBe StatusCodes.NoContent
    }
  }


  "return all courses" in {
    get("/courses") {
      val expectedCourses = Seq(
        CourseStub(
          id = "7341b1fc-3d80-4f6a-bcde-4fef86b01f95",
          teacher = "Nacho Vidal",
          listVideoId = Vector("7341b1fc-3d80-4f6a-bcde-4fef86b01f91","7341b1fc-3d80-4f6a-bcde-4fef86b01f93","7341b1fc-3d80-4f6a-bcde-4fef86b01f92")
        )
      )

      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe CourseMarshaller.marshall(expectedCourses)
    }
  }
}

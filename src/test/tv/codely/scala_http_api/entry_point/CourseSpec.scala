package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import spray.json._
import tv.codely.scala_http_api.module.courses.domain.CourseStub
import tv.codely.scala_http_api.module.courses.infrastructure.marshaller.CourseMarshaller

final class CourseSpec extends AcceptanceSpec {
  "return all courses" in {
    get("/courses") {
      val expectedCourses = Seq(
        CourseStub(
          id = "7341b1fc-3d80-4f6a-bcde-4fef86b01f95",
          teacher = "Nacho Vidal"
        ), CourseStub(
          id = "7341b1fc-3d80-4f6a-bcde-4fef86b01f93",
          teacher = "Jordi NP"
        )
      )

      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe CourseMarshaller.marshall(expectedCourses)
    }
  }
}

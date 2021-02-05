package tv.codely.scala_http_api.module.courses.infrastructure.marshaller

import spray.json.{JsArray, JsObject, JsString}
import tv.codely.scala_http_api.module.courses.domain.Course

object CourseJsValueMarshaller {
  def marshall(courses: Seq[Course]): JsArray = JsArray(
    courses
      .map(
        u =>
          JsObject(
            "id" -> JsString(u.id.value.toString),
            "teacher" -> JsString(u.teacher.value)
          )).toVector
  )
}

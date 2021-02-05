package tv.codely.scala_http_api.module.courses.infraestructure.marshaller

import spray.json.DefaultJsonProtocol.jsonFormat2
import spray.json.{DeserializationException, JsString, JsValue, JsonFormat, RootJsonFormat}
import tv.codely.scala_http_api.module.courses.domain.{Course, CourseId, CourseTeacherName}

import java.util.UUID

object CourseMarshaller {
  implicit object UuidMarshaller extends JsonFormat[UUID] {
    def write(value: UUID): JsValue = JsString(value.toString)

    def read(value: JsValue): UUID = value match {
      case JsString(uuid) => UUID.fromString(uuid)
      case _              => throw DeserializationException("Expected hexadecimal UUID string")
    }
  }

  implicit object IdMarshaller extends JsonFormat[CourseId] {
    def write(value: CourseId): JsValue = JsString(value.value.toString)

    def read(value: JsValue): CourseId = value match {
      case JsString(id) => CourseId(id)
      case _            => throw DeserializationException("Expected a string for a id")
    }
  }

  implicit object TeacherMarshaller extends JsonFormat[CourseTeacherName] {
    def write(value: CourseTeacherName): JsValue = JsString(value.value)

    def read(value: JsValue): CourseTeacherName = value match {
      case JsString(name) => CourseTeacherName(name)
      case _              => throw DeserializationException("Expected a string for teacher name")
    }
  }

  implicit val courseFormat: RootJsonFormat[Course] = jsonFormat2(
    Course.apply(_: CourseId, _: CourseTeacherName)
  )
}

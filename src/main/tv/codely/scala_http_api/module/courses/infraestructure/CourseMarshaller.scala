package tv.codely.scala_http_api.module.courses.infraestructure

import java.util.UUID

import spray.json.DefaultJsonProtocol._
import spray.json.{DeserializationException, JsArray, JsString, JsValue, JsonFormat, RootJsonFormat}
import tv.codely.scala_http_api.module.courses.domain.{Course, CourseId, CourseTeacherName, CourseVideoIdLists}
import tv.codely.scala_http_api.module.video.domain.VideoId

object CourseMarshaller {
  implicit object UuidMarshaller extends JsonFormat[UUID] {
    def write(value: UUID): JsValue = JsString(value.toString)

    def read(value: JsValue): UUID = value match {
      case JsString(uuid) => UUID.fromString(uuid)
      case _ => throw DeserializationException("Expected hexadecimal UUID string")
    }
  }

  implicit object IdMarshaller extends JsonFormat[CourseId] {
    def write(value: CourseId): JsValue = JsString(value.value.toString)

    def read(value: JsValue): CourseId = value match {
      case JsString(id) => CourseId(id)
      case _ => throw DeserializationException("Expected a string for a id")
    }
  }

  implicit object TeacherMarshaller extends JsonFormat[CourseTeacherName] {
    def write(value: CourseTeacherName): JsValue = JsString(value.value)

    def read(value: JsValue): CourseTeacherName = value match {
      case JsString(name) => CourseTeacherName(name)
      case _ => throw DeserializationException("Expected a string for teacher name")
    }
  }

  implicit object CourseVideoIdMarshaller extends JsonFormat[VideoId] {
    def write(value: VideoId): JsValue = JsString(value.value.toString)

    def read(value: JsValue): VideoId = value match {
      case JsString(id) => VideoId(id)
      case _ => throw DeserializationException("Expected a string for id Video")
    }
  }

  implicit object CourseVideoIdListMarshaller extends JsonFormat[CourseVideoIdLists] {
    def write(value: CourseVideoIdLists): JsValue = JsArray(value.value.map(elem => JsString(elem.value.toString)))

    def read(value: JsValue): CourseVideoIdLists = value match {
      case JsArray(idList) => idList match {
        case Vector(JsString(id)) =>  CourseVideoIdLists(Vector(VideoId(id)))
        case _ => throw DeserializationException("Expected a string for id Video")
      }
      case _ => throw DeserializationException("Expected a List for id list Video")
    }
  }

  implicit val courseFormat: RootJsonFormat[Course] = jsonFormat3(
    Course.apply(_: CourseId, _: CourseTeacherName, _: CourseVideoIdLists)
  )
}

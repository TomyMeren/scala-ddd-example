package tv.codely.scala_http_api.application.user.akkaHttp.marshaller

import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, JsonFormat}
import tv.codely.scala_http_api.application.user.api.{UserId, UserName}

import java.util.UUID

object UserIdJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object UserIdJsonFormatMarshaller extends JsonFormat[UserId] {
    override def write(value: UserId): JsValue = value.value.toJson

    override def read(value: JsValue): UserId = UserId(value.convertTo[UUID])
  }

  implicit object UserNamesonFormatMarshaller extends JsonFormat[UserName] {
    override def write(value: UserName): JsValue = JsString(value.value)

    override def read(value: JsValue): UserName = value match {
      case JsString(name) => UserName(name)
      case _              => throw DeserializationException("Expected 1 string for UserName")
    }
  }
}

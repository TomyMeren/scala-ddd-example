package tv.codely.scala_http_api.application.user.akkaHttp.marshaller

import spray.json.{DeserializationException, JsString, JsValue, JsonFormat}

import java.util.UUID

object UuidJsonFormatMarshaller {
  implicit object UuidMarshaller extends JsonFormat[UUID] {
    override def write(value: UUID): JsValue = JsString(value.toString)

    override def read(value: JsValue): UUID = value match {
      case JsString(uuid) => UUID.fromString(uuid)
      case unknown        => throw DeserializationException(s"Expected hexadecimal UUID string, got <$unknown>")
    }
  }
}

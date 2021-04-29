package tv.codely.scala_http_api.application.user.akkaHttp.marshaller

import spray.json.{DefaultJsonProtocol, JsValue, JsonFormat, enrichAny}
import tv.codely.scala_http_api.application.user.akkaHttp.marshaller.UuidJsonFormatMarshaller.UuidMarshaller
import tv.codely.scala_http_api.application.user.api.UserId

import java.util.UUID

object UserIdJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object UserIdMarshaller extends JsonFormat[UserId] {
    override def write(value: UserId): JsValue = value.value.toJson

    override def read(value: JsValue): UserId = UserId(value.convertTo[UUID])
  }
}

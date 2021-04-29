package tv.codely.scala_http_api.application.video.akkaHttp.marshaller

import spray.json.{DefaultJsonProtocol, DeserializationException, JsNumber, JsObject, JsString, JsValue, RootJsonFormat, _}
import tv.codely.scala_http_api.application.video.akkaHttp.marshaller.VideoAttributesJsonFormatMarshaller._
import tv.codely.scala_http_api.application.video.api.VideoCreated

object VideoCreatedJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object VideoCreatedJsonFormat extends RootJsonFormat[VideoCreated] {
    override def write(c: VideoCreated): JsValue = JsObject(
      "type"     -> JsString(c.`type`),
      "id"       -> c.id.toJson,
      "title"    -> c.title.toJson,
      "duration" -> c.duration.toJson,
      "category" -> c.category.toJson
    )

    override def read(value: JsValue): VideoCreated =
      value.asJsObject.getFields("id", "title", "duration", "category") match {
        case Seq(JsString(id), JsString(title), JsNumber(duration), JsString(category)) =>
          VideoCreated(id, title, duration, category)
        case unknown => throw DeserializationException(s"Error reading VideoCreated JSON <$unknown>")
      }
  }
}

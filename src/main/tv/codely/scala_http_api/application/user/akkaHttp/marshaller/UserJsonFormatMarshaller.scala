package tv.codely.scala_http_api.application.user.akkaHttp.marshaller

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import tv.codely.scala_http_api.application.user.akkaHttp.marshaller.UserIdJsonFormatMarshaller.UserIdMarshaller
import tv.codely.scala_http_api.application.user.akkaHttp.marshaller.UserNamesonFormatMarshaller.UserNameMarshaller
import tv.codely.scala_http_api.application.user.api.{User, UserId, UserName}

object UserJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val userFormat: RootJsonFormat[User] = jsonFormat2(User.apply(_: UserId, _: UserName))
}

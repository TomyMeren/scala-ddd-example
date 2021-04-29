package tv.codely.scala_http_api.application.system.http4s

import cats.effect.Effect
import io.circe._
import org.http4s._
import org.http4s.circe._
import tv.codely.scala_http_api.application.user.api.{User, UserId, UserName}

import io.circe.literal._
import io.circe.generic.auto._

object Decoders {
  //La original
  implicit def tupleStStDecoder[P[_]](implicit Ef: Effect[P]): EntityDecoder[P, (String, String)] =
    jsonOf[P, (String, String)]

  //Preparada para usar User
  implicit val UserIdFormat =
    new Encoder[UserId] with Decoder[UserId] {
      override def apply(a: UserId): Json =
        Encoder[String].apply(a.value.toString)
      override def apply(c: HCursor): Decoder.Result[UserId] =
        Decoder.decodeString.map(s => UserId(s)).apply(c)
    }

  implicit val UserNameFormat =
    new Encoder[UserName] with Decoder[UserName] {
      override def apply(a: UserName): Json =
        Encoder[String].apply(a.value)
      override def apply(c: HCursor): Decoder.Result[UserName] =
        Decoder.decodeString.map(s => UserName(s)).apply(c)
    }

  implicit def tupleStStDecoderUser[P[_]](implicit Ef: Effect[P]): EntityDecoder[P, User] =
    jsonOf[P, User]
}

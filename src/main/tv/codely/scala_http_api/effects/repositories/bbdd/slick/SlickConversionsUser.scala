package tv.codely.scala_http_api.effects.repositories.bbdd.slick

import slick.jdbc.{GetResult, SetParameter}
import tv.codely.scala_http_api.application.user.api.{User, UserId, UserName}

object SlickConversionsUser {

  implicit val getUsersResult:GetResult[User] =
    GetResult(user =>
      User(UserId(user.nextString()), UserName(user.nextString))
    )

  implicit val userIdSetter = SetParameter[UserId] {
    case (userId, params) => params.setString(userId.value.toString)
  }

  implicit val usernameSetter = SetParameter[UserName] {
    case (userName, params) => params.setString(userName.value)
  }
}

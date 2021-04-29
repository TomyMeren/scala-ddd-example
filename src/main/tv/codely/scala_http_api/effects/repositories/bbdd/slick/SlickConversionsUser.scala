package tv.codely.scala_http_api.effects.repositories.bbdd.slick

import slick.lifted.{CaseClassShape, Rep}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.{GetResult, SetParameter}
import tv.codely.scala_http_api.application.user.api.{User, UserId, UserName}

object SlickConversions {

  //case class UserSlick(id: UserId, name: UserName)
  case class UserLifted(id: Rep[UserId], name: Rep[UserName])

  implicit object UserNameShape extends CaseClassShape(UserLifted.tupled, User.tupled)

  implicit val getUsersResult:GetResult[User] =
    GetResult(user =>
      User(UserId(user.nextString()), UserName(user.nextString))
    )

  //TODO
  /*    implicit val uuidSetter = SetParameter[UUID]{
      case(uuid, params) => params.setString(uuid.toString)
    }*/

  implicit val userIdSetter = SetParameter[UserId] {
    case (userId, params) => params.setString(userId.value.toString)
  }

  implicit val usernameSetter = SetParameter[UserName] {
    case (userName, params) => params.setString(userName.value)
  }
}

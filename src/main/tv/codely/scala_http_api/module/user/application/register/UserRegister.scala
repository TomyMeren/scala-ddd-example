package tv.codely.scala_http_api.module.user.application.register

import tv.codely.scala_http_api.module.user.domain.{UserId, UserName}

trait UserRegister[P[_]] {
  def register(id: UserId, name: UserName): P[Unit]
}

package tv.codely.scala_http_api.application.system.api

import tv.codely.scala_http_api.application.user.api.{UserRegister, UsersSearcher}
import tv.codely.scala_http_api.application.video.api.{VideoCreator, VideoSearcher}

trait System[P[_]] {
  val usersSearcher:UsersSearcher[P]
  val userRegistrar: UserRegister[P]
  val videosSearcher: VideoSearcher[P]
  val videoCreator: VideoCreator[P]
}

package tv.codely.scala_http_api.application.system.repo_publisher

import cats.Apply
import tv.codely.scala_http_api.application.user.api.{UserRegister, UsersSearcher}
import tv.codely.scala_http_api.application.system.api.System
import tv.codely.scala_http_api.application.user.repo_publisher.{UserRegisterRepoPublisher, UsersSearcherRepo}
import tv.codely.scala_http_api.application.video.api.{VideoCreator, VideoSearcher}
import tv.codely.scala_http_api.application.video.repo_publisher.{VideoCreatorRepoPublisher, VideosSearcherRepo}
import tv.codely.scala_http_api.effects.bus.api._
import tv.codely.scala_http_api.effects.repositories.api.{UserRepository, VideoRepository}


final case class SystemRepoPublisher[P[_]]()(implicit userRepository: UserRepository[P],
                                           publisher: MessagePublisher[P],
                                           videoRepository: VideoRepository[P],
                                           F: Apply[P]) extends  System[P] {

  val usersSearcher:UsersSearcher[P] = UsersSearcherRepo[P]
  val userRegistrar: UserRegister[P] = UserRegisterRepoPublisher[P]
  val videosSearcher: VideoSearcher[P] = VideosSearcherRepo[P]
  val videoCreator: VideoCreator[P] = VideoCreatorRepoPublisher[P]
}

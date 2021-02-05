package tv.codely.scala_http_api.entry_point

import tv.codely.scala_http_api.entry_point.controller.courses.{CourseGetController, CoursePostController}
import tv.codely.scala_http_api.entry_point.controller.status.StatusGetController
import tv.codely.scala_http_api.entry_point.controller.user.{UserGetController, UserPostController}
import tv.codely.scala_http_api.entry_point.controller.video.{VideoGetController, VideoPostController}
import tv.codely.scala_http_api.module.courses.infraestructure.dependency_injection.CourseModuleDependencyContainer
import tv.codely.scala_http_api.module.user.infrastructure.dependency_injection.UserModuleDependecyContainer
import tv.codely.scala_http_api.module.video.infrastructure.dependecy_injection.VideoModuleDependecyContainer

final class EntryPointDependencyContainer(
  userDependencies: UserModuleDependecyContainer,
  videoDependecies: VideoModuleDependecyContainer,
  courseDependencies: CourseModuleDependencyContainer) {

  val statusGetController =   new StatusGetController

  val userGetController = new UserGetController(userDependencies.userSearcher)

  val videoGetController = new VideoGetController(videoDependecies.videoSearcher)

  val courseGetController = new CourseGetController(courseDependencies.courseSearcher)

  val userPostController = new UserPostController(userDependencies.userCreater)

  val videoPostController = new VideoPostController(videoDependecies.videoCreator)

  val coursePostController = new CoursePostController(courseDependencies.courseCreator)
}

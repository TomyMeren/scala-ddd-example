package tv.codely.scala_http_api.module.video.infrastructure.dependecy_injection

import tv.codely.scala_http_api.module.video.application.create.VideoCreator
import tv.codely.scala_http_api.module.video.application.search.VideoSearcher
import tv.codely.scala_http_api.module.video.infrastructure.repository.InMemoryVideoRepository

final class VideoModuleDependecyContainer {

  val repo = new InMemoryVideoRepository

  val videoSearcher = new VideoSearcher(repo)

  val videoCreator = new VideoCreator(repo)
}

package tv.codely.scala_http_api.module.video.infrastructure

import tv.codely.scala_http_api.module.IntegrationTestCase
import tv.codely.scala_http_api.module.video.domain.VideoRepository
import tv.codely.scala_http_api.module.video.infrastructure.dependecy_injection.VideoModuleDependecyContainer

protected[video] trait VideoIntegrationTestCase extends IntegrationTestCase {

  private val container = new VideoModuleDependecyContainer

  protected val repository: VideoRepository = container.repo
}

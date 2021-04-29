package tv.codely.scala_http_api.effects.repositories.mock.doobie

protected[video] trait VideoIntegrationTestCase extends IntegrationTestCase {
  private val container = new VideoModuleDependencyContainer(doobieDbConnection, messagePublisher)

  protected val repository: VideoRepository = container.repository
}

package tv.codely.scala_http_api.effects.repositories.mock.doobie

protected[user] trait UserIntegrationTestCase extends IntegrationTestCase {
  private val container = new UserModuleDependencyContainer(doobieDbConnection, messagePublisher)

  protected val repository: UserRepository = container.repository
}

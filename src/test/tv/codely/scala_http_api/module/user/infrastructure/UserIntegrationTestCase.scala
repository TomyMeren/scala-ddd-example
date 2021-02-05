package tv.codely.scala_http_api.module.user.infrastructure

import tv.codely.scala_http_api.module.IntegrationTestCase
import tv.codely.scala_http_api.module.user.infrastructure.dependency_injection.UserModuleDependecyContainer

protected[user] trait UserIntegrationTestCase extends IntegrationTestCase {

  private val container = new UserModuleDependecyContainer(dbConnection)

  protected val repository = container.repo
}

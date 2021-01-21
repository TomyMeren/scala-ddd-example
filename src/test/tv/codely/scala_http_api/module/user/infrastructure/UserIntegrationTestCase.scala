package tv.codely.scala_http_api.module.user.infrastructure

import tv.codely.scala_http_api.module.IntegrationTestCase
import tv.codely.scala_http_api.module.user.domain.UserRepository
import tv.codely.scala_http_api.module.user.infrastructure.repository.InMemoryUserRepository

protected[user] trait UserIntegrationTestCase extends IntegrationTestCase {

  protected val repository:UserRepository = new InMemoryUserRepository
}

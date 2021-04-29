package tv.codely.scala_http_api.effects.repositories.mock.doobie

import cats.effect.IO
import tv.codely.scala_http_api.effects.repositories.api.UserRepository
import tv.codely.scala_http_api.effects.repositories.bbdd.doobie.DoobieMySqlUserRepository

trait UserIntegrationTestCase extends IntegrationTestCase {

  protected val repository: UserRepository[IO] = DoobieMySqlUserRepository[IO]
}

package tv.codely.scala_http_api.effects.repositories.mock.doobie

import cats.effect.IO
import tv.codely.scala_http_api.effects.repositories.api.VideoRepository
import tv.codely.scala_http_api.effects.repositories.bbdd.doobie.DoobieMySqlVideoRepository

trait VideoIntegrationTestCase extends IntegrationTestCase {
  protected val repository: VideoRepository[IO] = DoobieMySqlVideoRepository[IO]
}

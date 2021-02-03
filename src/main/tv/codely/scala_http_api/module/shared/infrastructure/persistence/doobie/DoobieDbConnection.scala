package tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie

import cats.effect.IO
import doobie.syntax.ConnectionIOOps
import doobie.util.transactor.Transactor
import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig

import scala.concurrent.Future

final class DoobieDbConnection(config:DbConfig) {
  val transactor = Transactor.fromDriverManager[IO](
    config.driver,
    config.url,
    config.user,
    config.password
  )

  def read[T](query:ConnectionIOOps[T]):Future[T] = query.transact(transactor).unsafeToFuture()

}

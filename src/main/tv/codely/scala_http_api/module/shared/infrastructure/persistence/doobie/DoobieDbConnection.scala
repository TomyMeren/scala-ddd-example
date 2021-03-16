package tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie

import cats.effect.Async
import doobie.Transactor
import doobie.syntax.ConnectionIOOps
import doobie.util.transactor.Transactor.Aux
import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig

final class DoobieDbConnection[P[_]: Async](dbConfig: DbConfig) {
  val transactor: Aux[P, Unit] = Transactor.fromDriverManager[P](
    dbConfig.driver,
    dbConfig.url,
    dbConfig.user,
    dbConfig.password
  )

  def read[T](query: ConnectionIOOps[T]): P[T] =
    query.transact(transactor)//.unsafeToFuture()
}

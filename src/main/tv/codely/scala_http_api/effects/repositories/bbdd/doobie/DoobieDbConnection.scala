package tv.codely.scala_http_api.effects.repositories.bbdd.doobie

import cats.effect.Async
import doobie.{ConnectionIO, Transactor}
import doobie.util.transactor.Transactor.Aux
import doobie.syntax.connectionio._

final class DoobieDbConnection[P[_]: Async](dbConfig: JdbcConfig) {
  val transactor: Aux[P, Unit] = Transactor.fromDriverManager[P](
    dbConfig.driver,
    dbConfig.url,
    dbConfig.user,
    dbConfig.password
  )

  def read[T](query: ConnectionIO[T]): P[T] =
    query.transact(transactor)//.unsafeToFuture()
}

package tv.codely.scala_http_api.effects.repositories.bbdd.doobie

import cats.effect.Async
import doobie.syntax.connectionio._
import doobie.util.transactor.Transactor.Aux
import doobie.{ConnectionIO, Transactor}
import tv.codely.scala_http_api.effects.repositories.bbdd.api.{DBConnection, JdbcConfig}

final class DoobieDbConnection[P[_]: Async](dbConfig: JdbcConfig) extends DBConnection[ConnectionIO,P]{
  val transactor: Aux[P, Unit] = Transactor.fromDriverManager[P](
    dbConfig.driver,
    dbConfig.url,
    dbConfig.user,
    dbConfig.password
  )

  def read[T](query: ConnectionIO[T]): P[T] =
    query.transact(transactor)//.unsafeToFuture()
}

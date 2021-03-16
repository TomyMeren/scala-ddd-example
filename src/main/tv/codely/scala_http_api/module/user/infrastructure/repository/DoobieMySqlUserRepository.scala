package tv.codely.scala_http_api.module.user.infrastructure.repository

import cats.Monad
import cats.syntax.functor._
import doobie.implicits._
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.scala_http_api.module.user.domain.{User, UserRepository}
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.TypesConversions._

final class DoobieMySqlUserRepository[P[_]](db: DoobieDbConnection[P])(implicit M: Monad[P]) extends UserRepository[P] {
  override def all(): P[Seq[User]] = {
    sql"SELECT user_id, name FROM users".query[User].to[Seq]
      .transact(db.transactor)
  }

  override def save(user: User): P[Unit] =
    sql"INSERT INTO users(user_id, name) VALUES (${user.id}, ${user.name})".update.run
      .transact(db.transactor)
      .map(_ => ())
}

package tv.codely.scala_http_api.effects.repositories.bbdd.doobie

import cats.Monad
import cats.syntax.functor._
import doobie.implicits._
import tv.codely.scala_http_api.application.user.api.User
import tv.codely.scala_http_api.effects.repositories.api.UserRepository
import tv.codely.scala_http_api.effects.repositories.bbdd.doobie.TypesConversions._

final case class DoobieMySqlUserRepository[P[_]]()(implicit db: DoobieDbConnection[P], M: Monad[P]) extends UserRepository[P] {
  override def all(): P[Seq[User]] = {
    db.read(sql"SELECT user_id, name FROM users".query[User].to[Seq])
      //.transact(db.transactor)
  }

  override def save(user: User): P[Unit] =
    sql"INSERT INTO users(user_id, name) VALUES (${user.id}, ${user.name})".update.run
      .transact(db.transactor)
      .map(_ => ())
}

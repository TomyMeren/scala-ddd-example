package tv.codely.scala_http_api.effects.repositories.bbdd.slick

import slick.jdbc.MySQLProfile.api._
import tv.codely.scala_http_api.application.user.api.User
import tv.codely.scala_http_api.effects.repositories.api.UserRepository
import SlickConversionsUser._

import scala.concurrent.{ExecutionContext, Future}

final case class SlickMySqlUserRepository()(implicit connect: SlickDbConnection, ec:ExecutionContext) extends UserRepository[Future] {
  override def all(): Future[Seq[User]] = {
    val query:DBIO[Seq[User]] = sql"SELECT user_id, name FROM users".as[User]
    connect.read(query)
  }

  def save(user: User): Future[Unit] = {
    val insert: DBIO[Int] = sqlu"INSERT INTO users(user_id, name) VALUES (${user.id}, ${user.name})"

    connect.db
      .run(insert)
      .map(_ => ())
  }
}

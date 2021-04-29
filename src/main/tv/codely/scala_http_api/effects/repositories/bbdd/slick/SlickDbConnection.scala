package tv.codely.scala_http_api.effects.repositories.bbdd.slick

import slick.jdbc.MySQLProfile.api._
import tv.codely.scala_http_api.effects.repositories.bbdd.api.DBConnection

import scala.concurrent.Future

final class SlickDbConnection extends DBConnection[DBIO,Future]{

  val db:Database = Database.forConfig("database")

  def read[T](query:DBIO[T] ): Future[T] = {
    db.run[T](query)
  }
}

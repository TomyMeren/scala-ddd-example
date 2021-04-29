package tv.codely.scala_http_api.effects.repositories.bbdd.slick

import tv.codely.scala_http_api.effects.repositories.api.VideoRepository
import slick.jdbc.MySQLProfile.api._
import tv.codely.scala_http_api.application.video.api.Video
import SlickConversionsVideo._

import scala.concurrent.{ExecutionContext, Future}

final case class SlickMySqlVideoRepository()(implicit connect: SlickDbConnection, ec: ExecutionContext)
    extends VideoRepository[Future] {
  def all(): Future[Seq[Video]] = {
    val query = sql"SELECT video_id, title, duration_in_seconds, category FROM videos".as[Video]
    connect.read(query)
  }

  def save(video: Video): Future[Unit] = {
    val insert: DBIO[Int] =
      sqlu"INSERT INTO videos(video_id, title, duration_in_seconds, category) VALUES (${video.id}, ${video.title}, ${video.duration}, ${video.category})"

    connect.db
      .run(insert)
      .map(_ => ())
  }
}

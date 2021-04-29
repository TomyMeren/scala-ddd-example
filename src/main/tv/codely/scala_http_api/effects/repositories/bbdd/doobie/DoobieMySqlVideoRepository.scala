package tv.codely.scala_http_api.effects.repositories.bbdd.doobie

import cats.Monad
import cats.syntax.functor._
import doobie.implicits._
import tv.codely.scala_http_api.application.video.api.Video
import tv.codely.scala_http_api.effects.repositories.api.VideoRepository
import tv.codely.scala_http_api.effects.repositories.bbdd.doobie.TypesConversions._

final case class DoobieMySqlVideoRepository[P[_]: Monad]()(
  implicit
  db: DoobieDbConnection[P]
) extends VideoRepository[P] {
  override def all(): P[Seq[Video]] =
    db.read(sql"SELECT video_id, title, duration_in_seconds, category FROM videos".query[Video].to[Seq])
      //.transact(db.transactor)

  override def save(video: Video): P[Unit] =
    sql"INSERT INTO videos(video_id, title, duration_in_seconds, category) VALUES (${video.id}, ${video.title}, ${video.duration}, ${video.category})".update.run
      .transact(db.transactor)
      .map(_ => ())
}

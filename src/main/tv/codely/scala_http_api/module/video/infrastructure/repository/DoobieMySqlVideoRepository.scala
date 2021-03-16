package tv.codely.scala_http_api.module.video.infrastructure.repository

import cats.Monad, cats.syntax.functor._
import doobie.implicits._
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.scala_http_api.module.video.domain.{Video, VideoRepository}
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.TypesConversions._

final class DoobieMySqlVideoRepository[P[_]](db: DoobieDbConnection[P])(implicit M: Monad[P])
    extends VideoRepository[P] {
  override def all(): P[Seq[Video]] =
    sql"SELECT video_id, title, duration_in_seconds, category FROM videos".query[Video].to[Seq]
      .transact(db.transactor)

  override def save(video: Video): P[Unit] =
    sql"INSERT INTO videos(video_id, title, duration_in_seconds, category) VALUES (${video.id}, ${video.title}, ${video.duration}, ${video.category})".update.run
      .transact(db.transactor)
      .map(_ => ())
}

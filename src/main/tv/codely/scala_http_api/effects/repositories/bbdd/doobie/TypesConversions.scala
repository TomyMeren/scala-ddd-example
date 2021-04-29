package tv.codely.scala_http_api.effects.repositories.bbdd.doobie

import doobie.Meta
import tv.codely.scala_http_api.application.video.api.VideoCategory

import java.util.UUID
import scala.concurrent.duration.{Duration, DurationLong}

object TypesConversions {
  implicit val uuidMeta: Meta[UUID]                   = Meta[String].xmap(UUID.fromString, _.toString)
  implicit val durationMeta: Meta[Duration]           = Meta[Long].xmap(_.seconds, _.toSeconds)
  implicit val videoCategoryMeta: Meta[VideoCategory] = Meta[String].xmap(VideoCategory.apply, _.toString)
}

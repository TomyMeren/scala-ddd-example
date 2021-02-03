package tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie

import doobie.Meta
import tv.codely.scala_http_api.module.video.domain.VideoCategory

import java.util.UUID
import scala.concurrent.duration._

object TypesConversions  {
  implicit val uuidMeta:Meta[UUID] = Meta[String].xmap(UUID.fromString,_.toString)
  implicit val durationMeta:Meta[Duration] = Meta[Long].xmap(_.seconds,_.toSeconds)
  implicit val categoriesMeta:Meta[VideoCategory] = Meta[String].xmap(VideoCategory(_),_.toString)
}

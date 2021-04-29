package tv.codely.scala_http_api.application.video.api

import tv.codely.scala_http_api.module.video.domain.Video

trait VideoSearcherRepo[P[_]] {
  def all(): P[Seq[Video]]

}

package tv.codely.scala_http_api.application.video.api

trait VideoSearcher[P[_]] {
  def all(): P[Seq[Video]]
}

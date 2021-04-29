package tv.codely.scala_http_api.application.video.api

trait VideoCreator[P[_]] {
  def create(id: VideoId, title: VideoTitle, duration: VideoDuration, category: VideoCategory): P[Unit]
}

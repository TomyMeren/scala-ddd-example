package tv.codely.scala_http_api.module.courses.domain

import tv.codely.scala_http_api.module.video.domain.VideoId

object CourseVideoIdLists {
  def apply(value: => Vector[String]):CourseVideoIdLists = CourseVideoIdLists(value.map(video => VideoId(video)))
}

case class CourseVideoIdLists (value: Vector[VideoId])

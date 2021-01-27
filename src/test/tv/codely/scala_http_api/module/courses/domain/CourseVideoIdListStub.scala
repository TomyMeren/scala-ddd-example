package tv.codely.scala_http_api.module.courses.domain

import tv.codely.scala_http_api.module.shared.domain.IntStub
import tv.codely.scala_http_api.module.video.domain.{VideoId, VideoIdStub}

object CourseVideoIdListStub {

  def apply(value1: Vector[String]): CourseVideoIdLists = CourseVideoIdLists(value1.map(VideoIdStub(_)))

  def apply(value2: => Vector[VideoId]): CourseVideoIdLists = CourseVideoIdLists(value2)

  private val numberOfVideo: Int = IntStub.randomBetween(1, 20)

  def random: CourseVideoIdLists = CourseVideoIdLists((1 to numberOfVideo).map(_ => VideoIdStub.random).toVector)
}

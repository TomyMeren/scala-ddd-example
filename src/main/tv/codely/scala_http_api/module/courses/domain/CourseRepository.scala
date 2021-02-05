package tv.codely.scala_http_api.module.courses.domain

import scala.concurrent.Future

trait CourseRepository {

  def all():Future[Seq[Course]]

  def save(curso:Course):Future[Unit]
}

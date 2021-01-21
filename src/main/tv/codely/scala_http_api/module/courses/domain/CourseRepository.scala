package tv.codely.scala_http_api.module.courses.domain

trait CourseRepository {

  def all():Seq[Course]

  def save(curso:Course):Unit
}

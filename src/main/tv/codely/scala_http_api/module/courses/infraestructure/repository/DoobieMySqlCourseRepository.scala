package tv.codely.scala_http_api.module.courses.infraestructure.repository

import doobie.implicits._
import tv.codely.scala_http_api.module.courses.domain.{Course, CourseRepository}
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.TypesConversions._

import scala.concurrent.{ExecutionContext, Future}

final class DoobieMySqlCourseRepository(db: DoobieDbConnection)(implicit executionContext:ExecutionContext) extends CourseRepository {

  def all(): Future[Seq[Course]] =
    db.read(
      sql"SELECT course_id, teacher from courses"
        .query[Course]
        .to[Seq])

  def save(curso: Course): Future[Unit] =
    sql"INSERT INTO courses(course_id, teacher) VALUES(${curso.id}, ${curso.teacher})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())

}

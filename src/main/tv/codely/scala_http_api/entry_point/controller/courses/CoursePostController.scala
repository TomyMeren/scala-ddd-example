package tv.codely.scala_http_api.entry_point.controller.courses

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.module.courses.application.create.CourseCreator
import tv.codely.scala_http_api.module.courses.domain.{CourseId, CourseTeacherName}

final class CoursePostController (course:CourseCreator) extends SprayJsonSupport with DefaultJsonProtocol {
  def post(id:String,teacher:String) = {
    course.create(CourseId(id), CourseTeacherName(teacher))
    complete(HttpResponse(NoContent))
  }
}

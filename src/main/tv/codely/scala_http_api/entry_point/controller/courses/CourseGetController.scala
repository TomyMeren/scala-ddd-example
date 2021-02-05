package tv.codely.scala_http_api.entry_point.controller.courses

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.module.courses.application.search.CourseSearcher
import tv.codely.scala_http_api.module.courses.infraestructure.marshaller.CourseJsonFormatMarshaller._

final class CourseGetController(courses: CourseSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(courses.all())
}

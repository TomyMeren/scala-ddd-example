package tv.codely.scala_http_api.entry_point.controller.courses

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.module.courses.application.search.CourseSearcher

final class CursePostController(course:CourseSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
}

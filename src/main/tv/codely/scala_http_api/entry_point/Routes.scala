package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol.{IntJsonFormat, StringJsonFormat}
import spray.json.JsValue
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import scala.concurrent.duration.DurationInt

final class Routes(container: EntryPointDependencyContainer) {

  private val status = get {
    path("status")(container.statusGetController.get())
  }

  private val users = get {
    path("users")(container.userGetController.get())
  }

  private val videos = get {
    path("videos")(container.videoGetController.get())
  } ~
    post {
      path("videos") {
        jsonBody { body =>
          container.videoPostController.post(
            body("id").convertTo[String],
            body("title").convertTo[String],
            body("duration_in_seconds").convertTo[Int].seconds,
            body("category").convertTo[String]
          )
        }
      }
    }

  private val courses = get {
    path("courses")(container.courseGetController.get())
  } ~ post {
    path("courses") {
      jsonBody { body =>
        container.coursePostController.post(
          body("id").convertTo[String],
          body("teacher").convertTo[String]
        )
      }
    }
  }

  val all: Route = status ~ users ~ videos ~ courses

  private def jsonBody(hander: Map[String, JsValue] => Route): Route =
    entity(as[JsValue])(json => hander(json.asJsObject.fields))
}


package tv.codely.scala_http_api.application.system.akkaHttp

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol._
import spray.json.JsValue
import tv.codely.scala_http_api.application.system.api.System
import tv.codely.scala_http_api.application.user.akkaHttp.{UserGetController, UserPostController}
import tv.codely.scala_http_api.application.video.akkaHttp.{VideoGetController, VideoPostController}

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._

final class Routes(implicit system:System[Future],executionContext: ExecutionContext) {

  val statusGetController = new StatusGetController

  val userGetController  = new UserGetController(system.usersSearcher)
  val userPostController = new UserPostController(system.userRegistrar)

  val videoGetController  = new VideoGetController(system.videosSearcher)
  val videoPostController = new VideoPostController(system.videoCreator)

  private val status = get {
    path("status")(statusGetController.get())
  }

  private val user = get {
    path("users")(userGetController.get())
  } ~
    post {
      path("users") {
        jsonBody { body =>
          userPostController.post(
            body("id").convertTo[String],
            body("name").convertTo[String]
          )
        }
      }
    }

  private val video = get {
    path("videos")(videoGetController.get())
  } ~
    post {
      path("videos") {
        jsonBody { body =>
          videoPostController.post(
            body("id").convertTo[String],
            body("title").convertTo[String],
            body("duration_in_seconds").convertTo[Int].seconds,
            body("category").convertTo[String]
          )
        }
      }
    }

  val all: Route = status ~ user ~ video

  private def jsonBody(handler: Map[String, JsValue] => Route): Route =
    entity(as[JsValue])(json => handler(json.asJsObject.fields))
}
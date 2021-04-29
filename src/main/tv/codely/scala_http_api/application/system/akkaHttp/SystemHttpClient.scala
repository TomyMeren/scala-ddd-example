package tv.codely.scala_http_api.application.system.akkaHttp


import tv.codely.scala_http_api.application.video.api._
import tv.codely.scala_http_api.application.user.api._
import tv.codely.scala_http_api.application.user.api.UserId
import tv.codely.scala_http_api.application.user.akkaHttp.marshaller.UserJsonFormatMarshaller._
import tv.codely.scala_http_api.application.video.akkaHttp.marshaller.VideoJsonFormatMarshaller._
import tv.codely.scala_http_api.application.system.api.System
import scala.concurrent.Future
import akka.http.scaladsl.Http
import akka.util.ByteString
import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer

final class SystemHttpClient(
                              implicit
                              httpConfig: HttpServerConfig,
                              actorSystem: ActorSystem
                            ) extends System[Future] {
  implicit val ec                              = actorSystem.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  import spray.json._

  val userRegistrar = new UserRegister[Future] {
    def register(id: UserId, name: UserName): Future[Unit] =
      Http()
        .singleRequest(
          HttpRequest(
            method = HttpMethods.POST,
            uri = httpConfig.addPath("users"),
            entity = HttpEntity(
              MediaTypes.`application/json`,
              ByteString(User(id, name).toJson.compactPrint)
            )
          )
        )
        .map(_ => ())
  }

  val usersSearcher = new UsersSearcher[Future] {
    def all(): Future[Seq[User]] =
      Http().singleRequest(HttpRequest(method = HttpMethods.GET, uri = httpConfig.addPath("users"))).flatMap {
        case HttpResponse(StatusCodes.OK, _, entity, _) =>
          entity.dataBytes
            .runFold(ByteString(""))(_ ++ _)
            .map(_.utf8String.parseJson.convertTo[List[User]](DefaultJsonProtocol.listFormat[User]))
        case response => Future.failed(new RuntimeException(response.toString))
      }
  }

  val videoCreator = new VideoCreator[Future] {
    def create(
                id: VideoId,
                title: VideoTitle,
                duration: VideoDuration,
                category: VideoCategory
              ): Future[Unit] =
      Http()
        .singleRequest(
          HttpRequest(
            method = HttpMethods.POST,
            uri = httpConfig.addPath("videos"),
            entity = HttpEntity(
              MediaTypes.`application/json`,
              ByteString(Video(id, title, duration, category).toJson.compactPrint)
            )
          )
        )
        .map(_ => ())
  }

  val videosSearcher = new VideoSearcher[Future] {
    def all(): Future[Seq[Video]] =
      Http().singleRequest(HttpRequest(method = HttpMethods.GET, uri = httpConfig.addPath("videos"))).flatMap {
        case HttpResponse(StatusCodes.OK, _, entity, _) =>
          entity.dataBytes
            .runFold(ByteString(""))(_ ++ _)
            .map(_.utf8String.parseJson.convertTo[List[Video]](DefaultJsonProtocol.listFormat[Video]))
      }
  }
}

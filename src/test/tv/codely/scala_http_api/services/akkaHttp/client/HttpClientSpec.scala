package tv.codely.scala_http_api.services.akkaHttp.client

import akka.actor.ActorSystem
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.duration._
import tv.codely.scala_http_api.application.system.akkaHttp.{HttpServerConfig, SystemHttpClient}
import tv.codely.scala_http_api.services.stubs.user.UserStub
import tv.codely.scala_http_api.services.stubs.video.VideoStub

class HttpClientSpec extends WordSpec with Matchers with ScalaFutures {

  // Read configs

  val httpServerConfig = HttpServerConfig("http://localhost", 8080)

  // Inject dependencies

  implicit val actorSystem      = ActorSystem("scala-http-api-integration-test")
  implicit val executionContext = actorSystem.dispatcher
  val system                    = new SystemHttpClient()(httpServerConfig, actorSystem)

  // Run configuration

  "User search should work" ignore {
    assert(system.usersSearcher.all().isReadyWithin(10.seconds))
  }

  "User register should work" ignore {
    val user = UserStub.random
    assert(system.userRegistrar.register(user.id, user.name).isReadyWithin(10.seconds))
  }

  "Video search should work" ignore {
    assert(system.videosSearcher.all().isReadyWithin(10.seconds))
  }

  "Video creator should work" ignore {
    val video = VideoStub.random

    assert(
      system.videoCreator
        .create(video.id, video.title, video.duration, video.category)
        .isReadyWithin(10.seconds)
    )
  }
}

package tv.codely.scala_http_api.application.system.akkaHttp

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import tv.codely.scala_http_api.application.system.api.System

import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn

final case class SystemController()(implicit system: System[Future], ec: ExecutionContext) {

  val routes = new Routes()

  def run(serverConfig: HttpServerConfig)(implicit actorSystem: ActorSystem):Unit = {

    implicit val materializer: ActorMaterializer    = ActorMaterializer()

    val host            = serverConfig.host
    val port            = serverConfig.port

    val bindingFuture = Http().bindAndHandle(routes.all, host, port)

    bindingFuture.failed.foreach { t =>
      println(s"Failed to bind to http://$host:$port/:")
      pprint.log(t)
    }

    // let it run until user presses return
    println(s"Server online at http://$host:$port/\nPress RETURN to stop...")
    StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => actorSystem.terminate())
  }

}

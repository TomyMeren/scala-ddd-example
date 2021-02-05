package tv.codely.scala_http_api.entry_point

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import tv.codely.scala_http_api.module.courses.infraestructure.dependency_injection.CourseModuleDependencyContainer
import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig
import tv.codely.scala_http_api.module.shared.infrastructure.depency_injection.SharedModuleDependencyContainer
import tv.codely.scala_http_api.module.user.infrastructure.dependency_injection.UserModuleDependecyContainer
import tv.codely.scala_http_api.module.video.infrastructure.dependecy_injection.VideoModuleDependecyContainer

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}
import scala.io.StdIn

object ScalaHttpApi {
  def main(args: Array[String]): Unit = {
    val appConfig = ConfigFactory.load("application")
    val serverConfig = ConfigFactory.load("http-server")

    val actorSystemName = appConfig.getString("main-actor-system.name")
    val host = serverConfig.getString("http-server.host")
    val port = serverConfig.getInt("http-server.port")

    val dbConfig = DbConfig(appConfig.getConfig("database"))

    val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName, dbConfig)

    implicit val system: ActorSystem                = sharedDependencies.system
    implicit val materializer: ActorMaterializer    = sharedDependencies.materializer
    implicit val executionContext: ExecutionContext = sharedDependencies.executionContext

    val container:EntryPointDependencyContainer = new EntryPointDependencyContainer(
      new UserModuleDependecyContainer(sharedDependencies.doobieDbConnection),
      new VideoModuleDependecyContainer(sharedDependencies.doobieDbConnection),
      new CourseModuleDependencyContainer(sharedDependencies.doobieDbConnection)
    )

    val routes = new Routes(container)

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
      .onComplete(_ => system.terminate())
  }
}

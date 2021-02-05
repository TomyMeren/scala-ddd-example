package tv.codely.scala_http_api.module.shared.infrastructure.depency_injection

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContextExecutor

final class SharedModuleDependencyContainer(actorSystemName: String, config:DbConfig) {

  implicit val system: ActorSystem = ActorSystem(actorSystemName)
  val materializer: ActorMaterializer = ActorMaterializer()
  val executionContext: ExecutionContextExecutor = system.dispatcher

  val doobieDbConnection = new DoobieDbConnection(config:DbConfig)
}

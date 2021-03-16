package tv.codely.scala_http_api.module.shared.infrastructure.dependency_injection

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import cats.effect.IO
import tv.codely.scala_http_api.module.shared.domain.Logger
import tv.codely.scala_http_api.module.shared.infrastructure.config.{DbConfig, MessageBrokerConfig}
import tv.codely.scala_http_api.module.shared.infrastructure.logger.scala_logging.ScalaLoggingLogger
import tv.codely.scala_http_api.module.shared.infrastructure.message_broker.rabbitmq.{RabbitMqChannelFactory, RabbitMqMessagePublisher}
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

import scala.concurrent.ExecutionContext

final class SharedModuleDependencyContainer(
    actorSystemName: String,
    dbConfig: DbConfig,
    publisherConfig: MessageBrokerConfig
) {
  implicit val actorSystem: ActorSystem  = ActorSystem(actorSystemName)
  val materializer: ActorMaterializer    = ActorMaterializer()
  val executionContext: ExecutionContext = actorSystem.dispatcher

  val doobieDbConnection: DoobieDbConnection[IO] = new DoobieDbConnection[IO](dbConfig)

  private val rabbitMqChannelFactory     = new RabbitMqChannelFactory(publisherConfig)
  val messagePublisher = new RabbitMqMessagePublisher(rabbitMqChannelFactory)

  val logger: Logger = new ScalaLoggingLogger
}

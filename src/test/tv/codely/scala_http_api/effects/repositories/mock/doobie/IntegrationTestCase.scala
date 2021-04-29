package tv.codely.scala_http_api.effects.repositories.mock.doobie

import akka.actor.ActorSystem
import cats.effect.IO
import com.typesafe.config.ConfigFactory
import tv.codely.scala_http_api.application.system.akkaHttp.HttpServerConfig
import tv.codely.scala_http_api.effects.bus.rabbit_mq.{RabbitMqChannelFactory, RabbitMqConfig, RabbitMqMessagePublisher}
import tv.codely.scala_http_api.effects.logger.scala_logging.ScalaLoggingLogger
import tv.codely.scala_http_api.effects.repositories.bbdd.api.JdbcConfig
import tv.codely.scala_http_api.effects.repositories.bbdd.doobie.DoobieDbConnection
import tv.codely.scala_http_api.services.mock.UnitTestCase

trait IntegrationTestCase extends UnitTestCase {
  val appConfig        = ConfigFactory.load("application")
  val httpServerConfig = HttpServerConfig(ConfigFactory.load("http-server"))
  val dbConfig         = JdbcConfig(appConfig.getConfig("database"))
  val publisherConfig  = RabbitMqConfig(appConfig.getConfig("message-publisher"))
  val actorSystemName  = appConfig.getString("main-actor-system.name")

  protected implicit val actorSystem         = ActorSystem(actorSystemName)
  protected implicit val executionContext    = actorSystem.dispatcher
  protected implicit val doobieDbConnection  = new DoobieDbConnection[IO](dbConfig)
  protected implicit val messagePublisher   = RabbitMqMessagePublisher(publisherConfig)
  protected implicit val logger              = new ScalaLoggingLogger
  protected implicit val rabbitMqChannelFactory = new RabbitMqChannelFactory(publisherConfig)
}

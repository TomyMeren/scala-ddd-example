package tv.codely
package scala_http_api
package entry_point

import akka.actor.ActorSystem

import com.typesafe.config.ConfigFactory
import tv.codely.scala_http_api.application.system.akkaHttp.{HttpServerConfig, SystemController}
import tv.codely.scala_http_api.application.system.repo_publisher.SystemRepoPublisher
import tv.codely.scala_http_api.effects.bus.rabbit_mq.{RabbitMqConfig, RabbitMqMessagePublisher}
import scala.concurrent.Future
import cats.instances.future._
import tv.codely.scala_http_api.effects.repositories.api.{UserRepository, VideoRepository}
import tv.codely.scala_http_api.effects.repositories.bbdd.slick.{SlickDbConnection, SlickMySqlUserRepository, SlickMySqlVideoRepository}

//import tv.codely.scala_http_api.effects.repositories.bbdd.JdbcConfig
//import tv.codely.scala_http_api.effects.repositories.bbdd.doobie.{DoobieDbConnection, DoobieMySqlUserRepository, DoobieMySqlVideoRepository}
//import cats.effect.IO

object ScalaHttpApi {
  def main(args: Array[String]): Unit = {
    val appConfig    = ConfigFactory.load("application")
    val httpServerConfig = HttpServerConfig(ConfigFactory.load("http-server"))
    //val dbConfig        = JdbcConfig(appConfig.getConfig("database"))
    val publisherConfig = RabbitMqConfig(appConfig.getConfig("message-publisher"))
    val actorSystemName = appConfig.getString("main-actor-system.name")

    implicit val actorSystem = ActorSystem(actorSystemName)
    implicit val executionContext     = actorSystem.dispatcher

    /*implicit val doobieDbConnection = new DoobieDbConnection[IO](dbConfig)
    implicit val UserRepo = DoobieMySqlUserRepository[IO]
    implicit val VideoRepo = DoobieMySqlVideoRepository[IO]*/

    implicit val slickConnetion:SlickDbConnection = new SlickDbConnection
    implicit val UserRepo:UserRepository[Future] = SlickMySqlUserRepository()
    implicit val VideoRepo:VideoRepository[Future] = SlickMySqlVideoRepository()

    implicit val rabbitMqPublisher= RabbitMqMessagePublisher(publisherConfig)
    implicit val systemRepoPublisher = SystemRepoPublisher[Future]

    val akkaHttpSystem = SystemController()

    akkaHttpSystem.run(httpServerConfig)
  }
}

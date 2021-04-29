package tv.codely.scala_http_api
package entry_point

import cats.effect.IO

import com.typesafe.config.Config
import tv.codely.scala_http_api.application.system.api.System
import tv.codely.scala_http_api.application.system.http4s.Http4sSystemService
import tv.codely.scala_http_api.application.system.repo_publisher.SystemRepoPublisher
import tv.codely.scala_http_api.effects.bus.rabbit_mq.{RabbitMqConfig, RabbitMqMessagePublisher}
//import tv.codely.scala_http_api.effects.repositories.bbdd.JdbcConfig
//import tv.codely.scala_http_api.effects.repositories.bbdd.doobie.{DoobieDbConnection, DoobieMySqlUserRepository, DoobieMySqlVideoRepository}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future
import tv.codely.scala_http_api.effects.repositories.api.{UserRepository, VideoRepository}
import tv.codely.scala_http_api.effects.repositories.bbdd.slick.SlickDbConnection
import tv.codely.scala_http_api.effects.repositories.bbdd.slick.{SlickMySqlUserRepository, SlickMySqlVideoRepository}

object Http4sEntryPoint extends Http4sSystemService.App[IO](DoobieRabbitSystem.system)

object DoobieRabbitSystem {
  val system:Config => System[IO]  = { //IO
    case appConfig =>
//      val dbConfig        = JdbcConfig(appConfig.getConfig("database"))
      val publisherConfig = RabbitMqConfig(appConfig.getConfig("message-publisher"))

//      implicit val doobieDbConnection = new DoobieDbConnection[IO](dbConfig)
//      implicit val UserRepo = DoobieMySqlUserRepository[IO]
//      implicit val VideoRepo = DoobieMySqlVideoRepository[IO]

    implicit val slickConnetion:SlickDbConnection = new SlickDbConnection
      implicit val UserRepo:UserRepository[Future] = SlickMySqlUserRepository()
      implicit val VideoRepo:VideoRepository[Future] = SlickMySqlVideoRepository()


      implicit val rabbitMqPublisher = RabbitMqMessagePublisher(publisherConfig)
      SystemRepoPublisher[IO] //IO
  }
}


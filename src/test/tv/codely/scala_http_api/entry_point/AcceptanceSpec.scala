package tv.codely.scala_http_api
package entry_point

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString
import cats.effect.IO
import cats.instances.future._
import com.typesafe.config.ConfigFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import tv.codely.scala_http_api.application.system.akkaHttp.{HttpServerConfig, SystemController}
import tv.codely.scala_http_api.application.system.repo_publisher.SystemRepoPublisher
import tv.codely.scala_http_api.effects.bus.rabbit_mq.{RabbitMqConfig, RabbitMqMessagePublisher}
import tv.codely.scala_http_api.effects.repositories.bbdd.api.JdbcConfig
import tv.codely.scala_http_api.effects.repositories.bbdd.doobie.{DoobieDbConnection, DoobieMySqlUserRepository, DoobieMySqlVideoRepository}

import scala.concurrent.Future

protected[entry_point] abstract class AcceptanceSpec
    extends WordSpec
    with Matchers
    with ScalaFutures
    with ScalatestRouteTest {
  val appConfig        = ConfigFactory.load("application")
  val httpServerConfig = HttpServerConfig(ConfigFactory.load("http-server"))
  val dbConfig         = JdbcConfig(appConfig.getConfig("database"))
  val publisherConfig  = RabbitMqConfig(appConfig.getConfig("message-publisher"))
  val actorSystemName  = appConfig.getString("main-actor-system.name")

  //implicit val actorSystem         = ActorSystem(actorSystemName)
  implicit val executionContext    = system.dispatcher
  implicit val doobieDbConnection  = new DoobieDbConnection[IO](dbConfig)
  implicit val UserRepo            = DoobieMySqlUserRepository[IO]
  implicit val VideoRepo           = DoobieMySqlVideoRepository[IO]
  implicit val rabbitMqPublisher   = RabbitMqMessagePublisher(publisherConfig)
  implicit val systemRepoPublisher = SystemRepoPublisher[Future]

  val akkaHttpSystem = SystemController()

  protected def posting[T](path: String, request: String)(body: ⇒ T): T =
    HttpRequest(
      method = HttpMethods.POST,
      uri = path,
      entity = HttpEntity(
        MediaTypes.`application/json`,
        ByteString(request)
      )
    ) ~> akkaHttpSystem.routes.all ~> check(body)

  protected def getting[T](path: String)(body: ⇒ T): T = Get(path) ~> akkaHttpSystem.routes.all ~> check(body)
}

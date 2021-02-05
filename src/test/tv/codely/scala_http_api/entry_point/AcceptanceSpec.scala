package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString
import com.typesafe.config.ConfigFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import tv.codely.scala_http_api.module.courses.infraestructure.dependency_injection.CourseModuleDependencyContainer
import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig
import tv.codely.scala_http_api.module.shared.infrastructure.depency_injection.SharedModuleDependencyContainer
import tv.codely.scala_http_api.module.user.infrastructure.dependency_injection.UserModuleDependecyContainer
import tv.codely.scala_http_api.module.video.infrastructure.dependecy_injection.VideoModuleDependecyContainer

protected[entry_point] abstract class AcceptanceSpec
  extends WordSpec
    with Matchers
    with ScalaFutures
    with ScalatestRouteTest {

  val appConfig = ConfigFactory.load("application")
  val actorSystemName = appConfig.getString("main-actor-system.name")
  val dbConfig = DbConfig(appConfig.getConfig("database"))

  val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName,dbConfig)

  val doobieDbConnection = sharedDependencies.doobieDbConnection

  val userContainer = new UserModuleDependecyContainer(sharedDependencies.doobieDbConnection)
  val videoContainer = new VideoModuleDependecyContainer(sharedDependencies.doobieDbConnection)
  val courseContainer = new CourseModuleDependencyContainer(sharedDependencies.doobieDbConnection)

  private val routes = new Routes(
    new EntryPointDependencyContainer(
      userContainer,
      videoContainer,
      courseContainer
    )
  )

  def get[T](path: String)(body: => T): T = Get(path) ~> routes.all ~> check(body)

  def post[T](path: String, request: String)(body: => T): T = HttpRequest(
    method = HttpMethods.POST,
    uri = path,
    entity = HttpEntity(
      MediaTypes.`application/json`,
      ByteString(request)
    )
  ) ~> routes.all ~> check(body)
}

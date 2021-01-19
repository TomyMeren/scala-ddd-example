package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import tv.codely.scala_http_api.module.courses.infraestructure.dependency_injection.CourseModuleDependencyContainer
import tv.codely.scala_http_api.module.user.infrastructure.dependency_injection.UserModuleDependecyContainer
import tv.codely.scala_http_api.module.video.infrastructure.dependecy_injection.VideoModuleDependecyContainer

protected[entry_point] abstract class AcceptanceSpec
  extends WordSpec
    with Matchers
    with ScalaFutures
    with ScalatestRouteTest {

  private val routes = new Routes(
    new EntryPointDependencyContainer(
      new UserModuleDependecyContainer,
      new VideoModuleDependecyContainer,
      new CourseModuleDependencyContainer
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

package tv.codely.scala_http_api.module.courses.infrastructure

import tv.codely.scala_http_api.module.IntegrationTestCase
import tv.codely.scala_http_api.module.courses.infraestructure.dependency_injection.CourseModuleDependencyContainer

trait CourseIntegrationTestCase extends IntegrationTestCase {

  private val container = new CourseModuleDependencyContainer(dbConnection)

  protected val repository = container.repo
}

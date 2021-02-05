package tv.codely.scala_http_api.module

import com.typesafe.config.ConfigFactory
import org.scalatest.BeforeAndAfterEach
import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig
import tv.codely.scala_http_api.module.shared.infrastructure.depency_injection.SharedModuleDependencyContainer
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

protected[scala_http_api] trait IntegrationTestCase extends UnitTestCase with BeforeAndAfterEach {

  private val appConfig = ConfigFactory.load("application")
  private val actorSystemName = appConfig.getString("main-actor-system.name")
  private val dbConfig = DbConfig(appConfig.getConfig("database"))

  protected val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName,dbConfig)

  protected val dbConnection = sharedDependencies.doobieDbConnection

  protected val doobieDbConnection = new DoobieDbConnection(dbConfig)


}

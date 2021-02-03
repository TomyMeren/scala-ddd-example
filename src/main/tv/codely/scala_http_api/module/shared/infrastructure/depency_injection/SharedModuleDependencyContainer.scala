package tv.codely.scala_http_api.module.shared.infrastructure.depency_injection

import tv.codely.scala_http_api.module.shared.infrastructure.config.DbConfig
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.DoobieDbConnection

final class SharedModuleDependencyContainer(config:DbConfig) {

  val doobieDbConnection = new DoobieDbConnection(config:DbConfig)
}

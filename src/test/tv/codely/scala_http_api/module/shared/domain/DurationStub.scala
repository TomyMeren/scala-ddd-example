package tv.codely.scala_http_api.module.shared.domain

import scala.concurrent.duration.{Duration, DurationInt}

object DurationStub {
  def random: Duration = IntStub.randomUnsigned(3600).minutes
}

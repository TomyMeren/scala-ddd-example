package tv.codely.scala_http_api.services.stubs

import scala.concurrent.duration.{Duration, DurationInt}

object DurationStub {
  def random: Duration = IntStub.randomUnsigned().seconds
}

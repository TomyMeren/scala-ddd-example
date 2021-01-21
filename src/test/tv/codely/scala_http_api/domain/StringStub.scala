package tv.codely.scala_http_api.domain

import scala.util.Random

object StringStub {
  def random(numChars: Int): String = Random.alphanumeric take numChars mkString ""
}

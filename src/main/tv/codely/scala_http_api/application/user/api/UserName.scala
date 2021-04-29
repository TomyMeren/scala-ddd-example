package tv.codely.scala_http_api.application.user.api

import slick.lifted.MappedTo

case class UserName(value: String) extends MappedTo[String]

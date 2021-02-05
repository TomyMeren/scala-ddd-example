package tv.codely.scala_http_api.module.shared.domain

object SeqStub {

  val maximunElement = 10

  def randomOf[T](ele: => T):Seq[T] = (0 to IntStub.randomBetween(1, maximunElement)).map(_ => ele)

}

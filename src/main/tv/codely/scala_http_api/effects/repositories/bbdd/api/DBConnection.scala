package tv.codely.scala_http_api.effects.repositories.bbdd.api

//P: ConnectionIO o DBIO,
//Q: Generico P o Future
//T: Seq[User], Seq[Video]

trait DBConnection[P[_],Q[_]] {
  def read[T](query: P[T]): Q[T]
}

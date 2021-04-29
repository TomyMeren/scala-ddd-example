package tv.codely.scala_http_api.effects.bus.api

import cats.~>

trait MessagePublisher[P[_]] {
  def publish(message: Message): P[Unit]
}


object MessagePublisher {

  implicit def fromToMp[P[_], Q[_]](implicit mp: MessagePublisher[P], Q: P ~> Q): MessagePublisher[Q] = {
    new MessagePublisher[Q] {
      def publish(message: Message): Q[Unit] = Q(mp.publish(message))
    }
  }

  //necesario para test
  implicit def toQView[P[_], Q[_]](P: MessagePublisher[P])(
    implicit
    nat: P ~> Q
  ): MessagePublisher[Q] = fromToMp(P, nat)


}

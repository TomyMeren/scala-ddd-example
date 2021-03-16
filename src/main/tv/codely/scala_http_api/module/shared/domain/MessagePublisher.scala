package tv.codely.scala_http_api.module.shared.domain

import cats.~>

trait MessagePublisher[P[_]] {
  def publish(message: Message): P[Unit]
}


object MessagePublisher {
  /*  implicit def fromIdToFutureMp(mp: MessagePublisher[Id])(implicit ec :ExecutionContext): MessagePublisher[Future] = {
      new MessagePublisher[Future] {
        def publish(message:Message):Future[Unit] = Future(mp.publish(message))
      }
    }*/

  implicit def fromToMp[P[_], Q[_]](mp: MessagePublisher[P])(implicit Q: P ~> Q): MessagePublisher[Q] = {
    new MessagePublisher[Q] {
      def publish(message: Message): Q[Unit] = Q(mp.publish(message))
    }
  }


}

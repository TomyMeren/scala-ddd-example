package tv.codely

package object scala_http_api {

  import cats.effect.IO
  import cats.{~>, Id}

  import scala.concurrent.{ExecutionContext, Future}

  implicit def fromIdToFutureMp(implicit ec: ExecutionContext): Id ~> Future =
    new (Id ~> Future) {
      def apply[T](p: Id[T]): Future[T] = Future(p)
    }

  implicit def fromIdToIo: Id ~> IO =
    new (Id ~> IO) {
      def apply[T](p: Id[T]): IO[T] = IO(p)
    }

  implicit def fromIOToFuture: IO ~> Future =
    new (IO ~> Future) {
      def apply[T](p: IO[T]): Future[T] = p.unsafeToFuture
    }
}

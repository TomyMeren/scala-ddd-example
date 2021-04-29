package tv.codely

package object scala_http_api {
  import cats.effect.IO
  import cats.{~>, Id}

  import scala.concurrent.{ExecutionContext, Future}

  implicit def fromIdToFutureMp(implicit ec: ExecutionContext): Id ~> Future =
    new (Id ~> Future) {
      def apply[T](p: Id[T]): Future[T] = Future(p)
    }

  implicit def fromIOToFuture: IO ~> Future =
    new (IO ~> Future) {
      def apply[T](io: IO[T]): Future[T] = io.unsafeToFuture
    }

  implicit def fromFutureToIO: Future ~> IO =
    new (Future ~> IO) {
      def apply[T](fut: Future[T]): IO[T] = IO.fromFuture(IO(fut))
    }

  implicit def fromIdToIo: Id ~> IO =
    new (Id ~> IO) {
      def apply[T](p: Id[T]): IO[T] = IO(p)
    }
}

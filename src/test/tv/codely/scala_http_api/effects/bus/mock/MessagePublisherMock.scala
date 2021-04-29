package tv.codely.scala_http_api.effects.bus.mock

import cats.Id
import org.scalamock.scalatest.MockFactory
import tv.codely.scala_http_api.effects.bus.api.{Message, MessagePublisher}
import tv.codely.scala_http_api.services.mock.UnitTestCase

trait MessagePublisherMock extends MockFactory {
  this: UnitTestCase => // Make mandatory to also extend UnitTestCase in order to avoid using mocks in any other kind of test.

  protected val messagePublisher: MessagePublisher[Id] = mock[MessagePublisher[Id]]

  protected def publisherShouldPublish(message: Message): Unit = {
    (messagePublisher.publish _)
      .expects(message)
      .returning(())
  }
}

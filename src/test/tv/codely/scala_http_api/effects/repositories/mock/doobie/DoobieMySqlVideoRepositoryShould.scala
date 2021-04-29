package tv.codely.scala_http_api.effects.repositories.mock.doobie

import doobie.implicits._
import org.scalatest.BeforeAndAfterEach
import tv.codely.scala_http_api.services.stubs.video.VideoStub

final class DoobieMySqlVideoRepositoryShould extends VideoIntegrationTestCase with BeforeAndAfterEach {
  private def cleanVideosTable() =
    sql"TRUNCATE TABLE videos".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    cleanVideosTable()
  }

  //TODO: hemos tenido que poner unsafeToFuture() y  .unsafeToFuture().futureValue

  "return an empty sequence if there're no videos" in {
    println(repository.all())

    repository.all().unsafeToFuture.futureValue shouldBe Seq.empty
  }

  "search all existing videos" in {
    val videos = VideoStub.randomSeq

    videos.foreach(v => repository.save(v).unsafeToFuture)

    repository.all().unsafeToFuture.futureValue shouldBe videos
  }
}

package tv.codely.scala_http_api.module.video.infrastructure.repository

import doobie.implicits.{toSqlInterpolator, _}
import tv.codely.scala_http_api.module.video.domain.VideoStub
import tv.codely.scala_http_api.module.video.infrastructure.VideoIntegrationTestCase

protected[video] final class DoobieMySqlVideoRepositoryShould extends VideoIntegrationTestCase {

  def cleanTable() =
    sql"TRUNCATE TABLE videos".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    cleanTable()
  }

  "return and empty sequence if there're no videos" in {
    repository.all().futureValue shouldBe Seq.empty
  }

  "find all existing videos" in {
    val randomSeqVideo = VideoStub.randomSeqVideo

    randomSeqVideo.foreach(repository.save(_).futureValue)

    repository.all().futureValue shouldBe randomSeqVideo
  }
}

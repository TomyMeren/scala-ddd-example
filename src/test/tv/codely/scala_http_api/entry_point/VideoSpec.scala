package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import doobie.implicits._
import spray.json._
import tv.codely.scala_http_api.module.video.domain.VideoStub
import tv.codely.scala_http_api.module.video.infrastructure.marshaller.VideoJsValueMarshaller

final class VideoSpec extends AcceptanceSpec {

  def cleanTable() =
    sql"TRUNCATE TABLE videos".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  "post a video" in {
    post(
      "/videos",
      """
        |{
        | "id": "3dfb19ee-260b-420a-b08c-ed58a7a07aee",
        | "title": "🎥 Scala FTW vol. 1",
        | "duration_in_seconds": 15,
        | "category": "Screencast"
        |}
      """.stripMargin
    ) {
      status shouldBe StatusCodes.NoContent
    }
  }

  "return all the system videos" in {

    cleanTable()

/*    val expectedVideos = Seq(
      domain.VideoStub(
        id = "3dfb19ee-260b-420a-b08c-ed58a7a07aee",
        title = "🎥 Scala FTW vol. 1",
        duration = 15.second,
        category = "Screencast"
      )
    )*/

    val repository = videoContainer.repo
    val randomSeqVideo = VideoStub.randomSeqVideo
    randomSeqVideo.foreach(repository.save(_).futureValue)

    get("/videos") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe VideoJsValueMarshaller.marshall(randomSeqVideo)
    }
  }
}

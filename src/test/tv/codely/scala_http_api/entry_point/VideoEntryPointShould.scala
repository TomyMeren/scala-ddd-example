package tv.codely.scala_http_api.entry_point

import doobie.implicits._
import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import org.scalatest.BeforeAndAfterEach
import spray.json._
import tv.codely.scala_http_api.services.akkaHttp.marshaller.VideoJsValueMarshaller
import tv.codely.scala_http_api.services.stubs.video.VideoStub

final class VideoEntryPointShould extends AcceptanceSpec with BeforeAndAfterEach {
  private def cleanVideosTable() =
    sql"TRUNCATE TABLE videos".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    cleanVideosTable()
  }

  "save a video" in posting(
    "/videos",
    """
      |{
      |  "id": "c5e3774b-20bb-47d3-b9fa-9f9a6e75e280",
      |  "title": "Creando proyectos #Scala con SBT new ƛ🌈",
      |  "duration_in_seconds": 698,
      |  "category": "Screencast"
      |}
    """.stripMargin
  ) {
    status shouldBe StatusCodes.NoContent
  }

  "return all the videos" in {
    val videos = VideoStub.randomSeq

    videos.foreach(v => VideoRepo.save(v).unsafeToFuture)//Todo:¿??

    getting("/videos") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe VideoJsValueMarshaller.marshall(videos)
    }
  }
}

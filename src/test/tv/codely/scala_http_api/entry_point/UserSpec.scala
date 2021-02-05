package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import doobie.implicits._
import spray.json._
import tv.codely.scala_http_api.module.user.domain.UserStub
import tv.codely.scala_http_api.module.user.infrastructure.marshaller.UserjsValueMarshaller

final class UserSpec extends AcceptanceSpec {

  def cleanTable() =
    sql"TRUNCATE TABLE users".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  "post users" in {
    post("/users",
      """
        |{
        |  "id": "a11098af-d352-4cce-8372-2b48b97e6942",
        |  "name": "Codelyver️"
        |}
       """.stripMargin) {
      status shouldBe StatusCodes.NoContent
    }
  }

  "return all the system users" in {
    cleanTable()

    val repository = userContainer.repo
    val randomSeqUser = UserStub.randomSeqUser
    randomSeqUser.foreach(repository.save(_).futureValue)

    /*   val expectedUsers = Seq(
      UserStub(id = "deacd129-d419-4552-9bfc-0723c3c4f56a", name = "Edufasio")
    )
*/
    get("/users") {
      status shouldBe StatusCodes.OK
      contentType shouldBe ContentTypes.`application/json`
      entityAs[String].parseJson shouldBe UserjsValueMarshaller.marshall(randomSeqUser)
    }
  }
}

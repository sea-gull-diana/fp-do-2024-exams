package doit

import zio.*
import zio.http.*

object ZIOHttpClient extends ZIOAppDefault {

  val app =
    for {
      client <- ZIO.serviceWith[Client](_.host("localhost").port(8080))
      request = Request.get("hello").addQueryParam("name", "John")
      response <- client.request(request)
      _ <- response.body.asString.debug("Response")
    } yield ()

  def run = app.provide(Client.default, Scope.default)
}

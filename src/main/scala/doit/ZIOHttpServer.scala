package doit

import zio.*
import zio.http.*

object ZIOHttpServer extends ZIOAppDefault {
  val routes =
    Routes(
      Method.GET / Root -> handler(Response.text("Greetings at your service")),
      Method.GET / "hello" -> handler { (req: Request) =>
        val name = req.queryParamToOrElse("name", "World")
        Response.text(s"Hello $name!")
      }
    )

  def run = Server.serve(routes).provide(Server.default)
}

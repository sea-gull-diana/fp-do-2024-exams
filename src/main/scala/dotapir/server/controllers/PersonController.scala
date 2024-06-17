package dotapir.server.controllers

import zio.*
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.ztapir.*

import dotapir.http.endpoints.PersonEndpoints
import dotapir.model.User
import java.time.LocalDateTime
import java.time.ZonedDateTime

class PersonController extends BaseController {

  val create: ServerEndpoint[Any, Task] = PersonEndpoints.createEndpoint
    .zServerLogic { case (person) =>
      ZIO.succeed(
        User(person.name, person.age, person.pet, ZonedDateTime.now())
      )
    }

  val routes: List[ServerEndpoint[Any, Task]] =
    List(create)
}

object PersonController {
  def makeZIO: UIO[PersonController] =
    ZIO.succeed(new PersonController())
}

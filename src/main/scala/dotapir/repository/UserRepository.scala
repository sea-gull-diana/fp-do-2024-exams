package dotapir.repository

import zio.Task
import zio.ZLayer

import io.getquill.*
import io.getquill.jdbczio.Quill
import dotapir.model.User

// An interface of database operations to implement
trait UserRepository {
  def create(user: User): Task[User]
  def getById(id: Long): Task[Option[User]]
  def getAll(): Task[List[User]]
  def getByEmail(email: String): Task[Option[User]]
  def update(id: Long, op: User => User): Task[User]
  def delete(id: Long): Task[User]
}

class UserRepositoryLive private (quill: Quill.Postgres[SnakeCase])
    extends UserRepository {

  import quill.*

  inline given SchemaMeta[User] = schemaMeta[User]("users")
  inline given InsertMeta[User] = insertMeta[User](_.id, _.created)
  inline given UpdateMeta[User] = updateMeta[User](_.id, _.created)

  override def create(user: User): Task[User] =
    run(query[User].insertValue(lift(user)).returning(r => r))
  override def getById(id: Long): Task[Option[User]] =
    run(query[User].filter(_.id == lift(id))).map(_.headOption)
  // Get all users and map them into a list
  override def getAll(): Task[List[User]] =
    run(query[User]).map(_.toList)
  override def getByEmail(email: String): Task[Option[User]] =
    run(query[User].filter(_.email == lift(email))).map(_.headOption)

  override def update(id: Long, op: User => User): Task[User] =
    for {
      user <- getById(id).someOrFail(
        new RuntimeException(s"User $id not found")
      )
      updated <- run(
        query[User]
          .filter(_.id == lift(id))
          .updateValue(lift(op(user)))
          .returning(r => r)
      )
    } yield updated

  override def delete(id: Long): Task[User] =
    run(query[User].filter(_.id == lift(id)).delete.returning(r => r))
}

// Add layer to the dependency graph for dependency injection of UserRepositoryLive object
object UserRepositoryLive {
  def layer = ZLayer.fromFunction(UserRepositoryLive(_))
}

package dotapir.model

sealed abstract class ApplicationException(message: String)
    extends RuntimeException(message)

case class UnauthorizedException(message: String)
    extends ApplicationException(message)

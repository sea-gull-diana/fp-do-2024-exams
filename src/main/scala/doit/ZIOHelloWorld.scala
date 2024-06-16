package doit

import zio.*

object ZIOHelloWorld extends ZIOAppDefault {

  override def run: ZIO[Any & (ZIOAppArgs & Scope), Any, Any] =
    Console.printLine("Hello, World!")

}

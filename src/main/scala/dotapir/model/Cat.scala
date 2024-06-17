package dotapir.model

import sttp.tapir.Schema
import zio.json.JsonCodec

case class Cat(name: String) derives JsonCodec, Schema

package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{ByteArrayInputStream, InputStream}

import play.api.libs.json.Json

object JSONResponseBuilder extends ResponseBuilder {

  def createResponse(board: List[Symbol], filePath: String): InputStream = {
    val list = board.map { spot => spot.name }
    val json = Json.obj("board" -> Json.toJson(list))

    new ByteArrayInputStream(Json.stringify(json).getBytes)
  }
}

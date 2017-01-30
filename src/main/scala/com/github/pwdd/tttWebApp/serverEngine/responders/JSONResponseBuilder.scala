package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{ByteArrayInputStream, InputStream}

import com.github.pwdd.tttWebApp.tttEngine.GameData
import play.api.libs.json.Json

object JSONResponseBuilder extends ResponseBuilder {

  def createResponse(gameData: GameData): InputStream = {
    val list = gameData.board.map { spot => spot.name }
    val json = Json.obj("board" -> Json.toJson(list), "message" -> Json.toJson(gameData.message))

    new ByteArrayInputStream(Json.stringify(json).getBytes)
  }
}

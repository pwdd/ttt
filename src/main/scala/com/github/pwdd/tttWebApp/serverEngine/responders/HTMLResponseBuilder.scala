package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{ByteArrayInputStream, InputStream}

import com.github.pwdd.tttWebApp.tttEngine.{GameData, WebMessenger}

object HTMLResponseBuilder extends ResponseBuilder {
  def createResponse(gameData: GameData): InputStream = {
    val restartButton = "<br><a href=\"./\"><button>Restart</button></a>"

    new ByteArrayInputStream(
      (htmlStart +
        WebMessenger.strBoard(gameData.board) +
        restartButton +
        gameData.message +
        htmlEnd).getBytes)
  }
}

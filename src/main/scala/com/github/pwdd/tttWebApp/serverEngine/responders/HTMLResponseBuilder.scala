package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{ByteArrayInputStream, InputStream}

import com.github.pwdd.tttWebApp.tttEngine.{GameData, WebMessenger}

object HTMLResponseBuilder extends ResponseBuilder {
  def createResponse(gameData: GameData): InputStream = {
    new ByteArrayInputStream(
      (htmlStart +
        gameData.message +
        WebMessenger.strBoard(gameData.board) +
        htmlEnd).getBytes)
  }
}

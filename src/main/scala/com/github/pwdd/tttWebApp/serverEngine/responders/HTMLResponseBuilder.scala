package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{FileInputStream, InputStream}

import com.github.pwdd.tttWebApp.tttEngine.{WebMessenger, WebView}

object HTMLResponseBuilder extends ResponseBuilder {
  def createResponse(board: List[Symbol], filePath: String): InputStream = {
    val message = WebMessenger.strBoard(board)
    new WebView(filePath).printMessage(message)
    new FileInputStream(filePath)
  }
}

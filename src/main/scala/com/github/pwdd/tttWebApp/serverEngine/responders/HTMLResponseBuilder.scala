package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{ByteArrayInputStream, FileInputStream, InputStream}

import com.github.pwdd.tttWebApp.tttEngine.{WebMessenger, WebView}

object HTMLResponseBuilder extends ResponseBuilder {
  def createResponse(board: List[Symbol]): InputStream = {
    new ByteArrayInputStream(WebMessenger.strBoard(board).getBytes)
  }
}

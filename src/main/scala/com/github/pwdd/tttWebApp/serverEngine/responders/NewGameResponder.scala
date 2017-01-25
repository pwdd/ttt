package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{InputStream, ByteArrayInputStream, IOException}

case object NewGameResponder extends TResponder {
  def canRespond(fullURI: String): Boolean = fullURI == "/"

  def header(fullURI: String, date: String): InputStream = {
    val CRLF = protocolSettings.CRLF

    new ByteArrayInputStream(
      (protocolSettings.version + " " + protocolSettings.statusCodes.get("200") + CRLF +
        "Date: " + date + CRLF +
        "Content-Type: text/html" + CRLF +
        CRLF).getBytes)
  }

  @throws[IOException]
  def body(fullURI: String): InputStream =  {
    HTMLResponseBuilder.createResponse(GameAdapter.newGame)
  }
}
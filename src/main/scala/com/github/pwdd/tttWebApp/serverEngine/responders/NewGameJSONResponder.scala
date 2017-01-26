package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{ByteArrayInputStream, IOException, InputStream}

object NewGameJSONResponder extends TResponder {
  def canRespond(fullURI: String): Boolean = {
    fullURI.toLowerCase.matches("/new.json/?")
  }

  def header(fullURI: String, date: String): InputStream = {
    val CRLF = protocolSettings.CRLF

    new ByteArrayInputStream(
      (protocolSettings.version + " " + protocolSettings.statusCodes.get("200") + CRLF +
        "Date: " + date + CRLF +
        "Content-Type: application/json" + CRLF +
        CRLF).getBytes)
  }

  @throws[IOException]
  def body(fullURI: String): InputStream = {
    JSONResponseBuilder.createResponse(GameAdapter.newGame)
  }
}

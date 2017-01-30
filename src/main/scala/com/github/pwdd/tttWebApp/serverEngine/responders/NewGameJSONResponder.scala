package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{ByteArrayInputStream, IOException, InputStream}

import com.github.pwdd.tttWebApp.tttEngine.GameData

object NewGameJSONResponder extends TResponder {
  def canRespond(fullURI: String): Boolean = {
    val re = "^/new.json/?$".r
    re.findFirstIn(fullURI.toLowerCase).nonEmpty
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
    JSONResponseBuilder.createResponse(GameData(GameAdapter.newGame, ""))
  }
}

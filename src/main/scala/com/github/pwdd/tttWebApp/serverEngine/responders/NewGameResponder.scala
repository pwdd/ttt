package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{ByteArrayInputStream, IOException, InputStream}
import java.nio.file.Paths

import com.github.pwdd.tttWebApp.tttEngine.GameData

case object NewGameResponder extends TResponder {
  def canRespond(fullURI: String): Boolean = {
    val path = Paths.get(System.getProperty("user.dir"), "/public").toString.toLowerCase
    val requested = fullURI.toLowerCase
    requested == path || requested == path + "/"
  }

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
    HTMLResponseBuilder.createResponse(GameData(GameAdapter.newGame, "<h2></h2>"))
  }
}
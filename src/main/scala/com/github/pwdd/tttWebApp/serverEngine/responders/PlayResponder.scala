package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{ByteArrayInputStream, IOException, InputStream}

case class PlayResponder(val requestBody: String) extends TResponder {

  def canRespond(fullURI: String): Boolean = fullURI.toLowerCase.matches("/play/?")

  def header(fullURI: String, date: String): InputStream = {
    val CRLF = protocolSettings.CRLF

    new ByteArrayInputStream(
      (protocolSettings.version + " " + protocolSettings.statusCodes.get("200") + CRLF +
        "Date: " + date + CRLF +
        "Content-Type: text/html" + CRLF +
        CRLF).getBytes)
  }

  @throws[IOException]
  def body(fullURI: String): InputStream = {
    GameAdapter.play(formMap)
  }

  val formMap: Map[String, String] = {
    val formList = requestBody.replaceAll("&|=", " ").split(" ")
    val keys = formList.filter(word => formList.indexOf(word) % 2 == 0)
    val values = formList.filter(word => formList.indexOf(word) %2 != 0)
    (keys zip values).toMap
  }
}

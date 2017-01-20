package com.github.pwdd.tttWebApp.serverEngine.responders
import java.io.{ByteArrayInputStream, FileInputStream, IOException, InputStream}
import java.nio.file.Paths

import com.github.pwdd.tttWebApp.serverEngine.protocols.ProtocolSettings

object NewGameResponder extends TResponder {
  val protocolSettings = ProtocolSettings

  def canRespond(fullURI: String): Boolean = {
    val path: String = Paths.get(System.getProperty("user.dir"), "public").toString.toLowerCase
    fullURI.toLowerCase == path
  }

  def header(fullURI: String, date: String): InputStream = {
    new ByteArrayInputStream(
      (protocolSettings.version + " " + protocolSettings.statusCodes.get("200") + CRLF +
      "Date: " + date + CRLF +
      "Content-Type: text/html" + CRLF +
      "Content-Length: " + size(fullURI) + CRLF +
      CRLF).getBytes)
  }

  @throws[IOException]
  def body(fullURI: String): InputStream =  new FileInputStream(fullURI + "/base.html")

}
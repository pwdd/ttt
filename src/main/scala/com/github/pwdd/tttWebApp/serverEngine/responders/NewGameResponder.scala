package com.github.pwdd.tttWebApp.serverEngine.responders
import java.io._
import java.nio.file.Paths

object NewGameResponder extends TResponder {
  def canRespond(fullURI: String): Boolean = {
    val path: String = Paths.get(System.getProperty("user.dir"), "public").toString.toLowerCase
    fullURI.toLowerCase == path
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
    new FileInputStream(fullURI + "/base.html")
  }
}
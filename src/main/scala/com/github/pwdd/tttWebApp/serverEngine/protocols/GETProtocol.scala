package com.github.pwdd.tttWebApp.serverEngine.protocols
import java.io.{ByteArrayInputStream, InputStream}
import java.util

import com.github.pwdd.HTTPServer.responders.IResponder

case class GETProtocol(rootDirectoryOverride: String, requestOverride: util.HashMap[String, String], respondersOverride: Array[IResponder])
  extends AbsProtocol(rootDirectoryOverride, requestOverride, respondersOverride) {

  def errorMessage(date: String): InputStream = {
    val CRLF = protocolSettings.CRLF

    new ByteArrayInputStream(
      (protocolSettings.version + " " + protocolSettings.statusCodes.get("404") + CRLF +
        "Date: " + date + CRLF +
        "Content-Type: text/html" + CRLF +
        CRLF).getBytes)
  }
}
package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import com.github.pwdd.HTTPServer.responders.IResponder

case class POSTProtocol(rootDir: String,
                        requested: util.HashMap[String, String],
                        respondersList: Array[IResponder])
  extends AbsProtocol(rootDir, requested, respondersList) {

  def errorMessage(date: String): InputStream = {
    val CRLF = protocolSettings.CRLF
    val message = protocolSettings.statusCodes.get("400")

    new ByteArrayInputStream(
      (protocolSettings.version + " " + message + CRLF +
        "Date: " + date + CRLF +
        "Content-Type: text/html" + CRLF +
        CRLF +
        message).getBytes)
  }
}

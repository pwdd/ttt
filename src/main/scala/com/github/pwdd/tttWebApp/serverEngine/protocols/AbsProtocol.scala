package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.io.InputStream
import java.util

import com.github.pwdd.HTTPServer.responders.IResponder

abstract class AbsProtocol(rootDirectory: String, request: util.HashMap[String, String], responders: Array[IResponder])
  extends com.github.pwdd.HTTPServer.protocols.AProtocol(rootDirectory, request, responders) {

  val protocolSettings = ProtocolSettings

  def errorMessage(date: String): InputStream
}

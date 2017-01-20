package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.{IOException, InputStream}
import java.nio.file.{Files, Path, Paths}

import com.github.pwdd.tttWebApp.serverEngine.protocols.ProtocolSettings

trait TResponder extends com.github.pwdd.HTTPServer.responders.IResponder {
  val protocolSettings = ProtocolSettings
  val CRLF: String = "\r\n"

  def canRespond(fullURI: String): Boolean

  def header(fullURI: String, date: String): InputStream

  @throws[IOException]
  def body(fullURI: String): InputStream
}

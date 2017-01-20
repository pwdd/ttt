package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.util

trait TProtocolFactory extends com.github.pwdd.HTTPServer.protocols.AProtocolFactory {
  def createProtocol(rootDirectory: String, request: util.HashMap[String, String]): TProtocol
}

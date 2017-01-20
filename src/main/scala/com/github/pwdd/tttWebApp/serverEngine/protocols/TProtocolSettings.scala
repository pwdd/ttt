package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.util

trait TProtocolSettings extends com.github.pwdd.HTTPServer.protocols.IProtocolSettings {
  val statusCodes: util.HashMap[String, String]
  val version: String
}

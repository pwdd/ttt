package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.util

object ProtocolSettings extends TProtocolSettings {
  val statusCodes: util.HashMap[String, String] = new util.HashMap[String, String]() {{
    put("200", "200 OK")
    put("404", "404 Not Found")
  }}

  val version: String = "HTTP/1.1"
}

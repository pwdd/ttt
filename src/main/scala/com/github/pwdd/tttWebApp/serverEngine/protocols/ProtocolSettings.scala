package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.util

object ProtocolSettings extends TProtocolSettings {
  val CRLF = "\r\n"

  val statusCodes: util.HashMap[String, String] = new util.HashMap[String, String]() {{
    put("200", "200 OK")
    put("404", "404 Not Found")
    put("400", "400 Bad Request")
  }}

  val version: String = "HTTP/1.1"
}

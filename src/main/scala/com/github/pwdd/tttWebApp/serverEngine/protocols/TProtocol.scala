package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.io.InputStream

trait TProtocol extends com.github.pwdd.HTTPServer.protocols.AProtocol {
  val errorMessage: InputStream
}

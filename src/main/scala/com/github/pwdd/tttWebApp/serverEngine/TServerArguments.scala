package com.github.pwdd.tttWebApp.serverEngine

import com.github.pwdd.tttWebApp.serverEngine.protocols.TProtocolFactory

trait TServerArguments extends com.github.pwdd.HTTPServer.ServerArguments {
  def protocolFactory(): TProtocolFactory
}

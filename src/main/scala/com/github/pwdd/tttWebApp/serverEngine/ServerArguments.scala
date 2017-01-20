package com.github.pwdd.tttWebApp.serverEngine

import com.github.pwdd.tttWebApp.serverEngine.protocols.{ProtocolFactory, TProtocolFactory}

object ServerArguments extends TServerArguments {
  def protocolFactory(): TProtocolFactory = ProtocolFactory
}

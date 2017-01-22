package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.util

import com.github.pwdd.tttWebApp.serverEngine.responders.{NewGameResponder, PlayResponder}

object ProtocolFactory extends TProtocolFactory {
  def createProtocol(rootDirectory: String, request: util.HashMap[String, String]): AbsProtocol = {
    if (request.get("Method").toUpperCase == "GET") GETProtocol(rootDirectory, request, Array(NewGameResponder))
    else POSTProtocol(rootDirectory, request, Array(PlayResponder(request.get("Body"))))
  }
}

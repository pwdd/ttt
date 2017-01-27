package com.github.pwdd.tttWebApp.serverEngine.protocols

import java.util

import com.github.pwdd.tttWebApp.serverEngine.responders._

object ProtocolFactory extends TProtocolFactory {
  def createProtocol(rootDirectory: String, request: util.HashMap[String, String]): AbsProtocol = {

    if (request.get("Method").toUpperCase == "GET") {
      GETProtocol(rootDirectory,
        request,
        Array(NewGameResponder, NewGameJSONResponder, FileResponder))
    }

    else {
      val rb = request.get("Body")
      POSTProtocol(
        rootDirectory,
        request,
        Array(PlayResponder(rb), PlayJSONResponder(rb)))
    }
  }
}

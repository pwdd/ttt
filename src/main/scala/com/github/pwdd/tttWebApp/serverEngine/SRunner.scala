package com.github.pwdd.tttWebApp.serverEngine

object SRunner {
  def start(args: Array[String]): Unit = {
    println("Server running at localhost:" + ServerArguments.getPortNumber(args))
    new com.github.pwdd.HTTPServer.Runner(ServerArguments).start(args)
  }
}

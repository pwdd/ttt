package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.InputStream

import com.github.pwdd.tttWebApp.tttEngine.GameData

trait ResponseBuilder {
  val htmlStart = "<!doctype html><html><head><title>TTT</title></head><body>"
  val htmlEnd = "</body></html>"

  def createResponse(gameData: GameData): InputStream
}

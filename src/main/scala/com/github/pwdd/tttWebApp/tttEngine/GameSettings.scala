package com.github.pwdd.tttWebApp.tttEngine

object GameSettings {
  val board = com.github.pwdd.tttcore.Board
  val boardLength = 9
  val settings = com.github.pwdd.tttcore.Settings
  val firstPlayerMarker: Symbol = settings.firstPlayer
  val secondPlayerMarker: Symbol = settings.secondPlayer
  val emptySpot: Symbol = board.emptySpot
}